package com.example.workmanagerdemo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.work.*
import androidx.work.impl.utils.futures.SettableFuture
import com.example.workmanagerdemo.databinding.ActivityMainBinding
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.TimeUnit

const val TAG = "WorkManager"

class MainActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityMainBinding

    private lateinit var workRequest: WorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_one_time -> {
                // 创建请求
                val workRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
                // 提交
                WorkManager.getInstance(this).enqueue(workRequest)
            }
            R.id.btn_period -> {
                val constraints = Constraints.Builder()
                    .setRequiresBatteryNotLow(true) //设置为true，表示电量不足时停止工作
//                    .setRequiresCharging(true) //设置为true，表示只能在充电时工作
//                    .setRequiresDeviceIdle(true) //设置为true，表示系统在空闲状态时工作
//                    .setRequiresStorageNotLow(true) //设置为true，表示存储空间不足时停止工作
                    .build()
                val workRequest: WorkRequest =
                    // 周期至少15分钟一次，小于15分钟按15分钟算
                    PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.MINUTES)
                        // 设置约束
                        .setConstraints(constraints)
                        .build()
                // 提交
                WorkManager.getInstance(this).enqueue(workRequest)
            }
            R.id.btn_delay -> {
                workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .addTag("workGroup")
                    .build()
                WorkManager.getInstance(this).enqueue(workRequest)
            }
            R.id.btn_cancel -> {
                WorkManager.getInstance(this).cancelWorkById(workRequest.id)
            }
            R.id.btn_params -> {
                val request: WorkRequest = OneTimeWorkRequestBuilder<ParamsWorker>()
                    // 设置参数
                    .setInputData(workDataOf("name" to "小明", "age" to 18, "sex" to true))
                    .build()
                WorkManager.getInstance(this).enqueue(request)
            }
            R.id.btn_expedited -> {
                val request: WorkRequest = OneTimeWorkRequestBuilder<ExpeditedWorker>()
                    // 设置加急模式
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .build()
                /*
                  Caused by: java.util.concurrent.ExecutionException: java.lang.IllegalStateException:
                     Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`
                    在Android_12及以前的版本，需要使用getForegroundInfoAsync()方法
                 */
                WorkManager.getInstance(this).enqueue(request)
            }
            R.id.btn_coroutine_work -> {
                val request: WorkRequest = OneTimeWorkRequestBuilder<MyCoroutineWorker>()
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .build()
                WorkManager.getInstance(this).enqueue(request)
            }
            R.id.btn_retry -> {
                val request: WorkRequest = OneTimeWorkRequestBuilder<RetryWorker>()
                    .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS, //最短退避延迟时间设置为允许的最小值，即 10 秒。
                        TimeUnit.MILLISECONDS
                    ).build()
                WorkManager.getInstance(this).enqueue(request)
            }
            R.id.btn_observe -> {
                val request: WorkRequest = OneTimeWorkRequestBuilder<FailureWorker>().build()
                // 通过id观察Work
                WorkManager.getInstance(this)
                    .getWorkInfoByIdLiveData(request.id)
                    .observe(this) {
                        when (it.state) {
                            WorkInfo.State.SUCCEEDED -> {
                                Log.e(TAG, "成功")
                            }
                            WorkInfo.State.FAILED -> {
                                Log.e(TAG, "失败")
                            }
                            WorkInfo.State.ENQUEUED -> {
                                Log.e(TAG, "排队")
                            }
                            WorkInfo.State.RUNNING -> {
                                Log.e(TAG, "运行")
                            }
                        }
                    }
                WorkManager.getInstance(this).enqueue(request)
            }
            R.id.btn_link_work -> {
                val work1 = OneTimeWorkRequestBuilder<DataWorker>()
                    .setInputData(workDataOf("name" to "小白", "delay" to 1000L))
                    .build()
                val work2 = OneTimeWorkRequestBuilder<DataWorker>()
                    .setInputData(workDataOf("sex" to true, "delay" to 2000L))
                    .build()
                val work3 = OneTimeWorkRequestBuilder<DataWorker>()
                    .setInputData(workDataOf("age" to 18, "delay" to 3000L))
                    .build()
                val cache = OneTimeWorkRequestBuilder<CacheWorker>()
//                    .setInputMerger(OverwritingInputMerger::class)
                    .setInputMerger(ArrayCreatingInputMerger::class)
                    .build()
                val upload = OneTimeWorkRequestBuilder<UploadWorker>().build()

                WorkManager.getInstance(this)
                    .beginWith(listOf(work1, work2, work3)) //这3个工作是并行执行的
                    .then(cache)
                    .then(upload)
                    .enqueue()
            }
        }
    }
}

////////////////////////////////////////////////////

// 定义Worker
class MyWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "成功：${Thread.currentThread().name}")
        return Result.success()
    }
}

class FailureWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "失败：${Thread.currentThread().name}")
        return Result.failure()
    }
}

class RetryWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "重试：${Thread.currentThread().name}")
        return Result.retry()
    }
}

////////////////////////////////////////////////////

class ParamsWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val name = inputData.getString("name")
        val age = inputData.getInt("age", -1)
        val sex = inputData.getBoolean("sex", false)
        Log.e(TAG, "name:${name} age:${age} sex:${sex}")
        return Result.retry()
    }
}

////////////////////////////////////////////////////

class ExpeditedWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.e(TAG, "线程：${Thread.currentThread().name}")
        return Result.success()
    }

    @SuppressLint("RestrictedApi")
    override fun getForegroundInfoAsync(): ListenableFuture<ForegroundInfo> {
        val future = SettableFuture.create<ForegroundInfo>()
        future.set(getForegroundInfo())
        return future
    }

    private fun getForegroundInfo(): ForegroundInfo {
        val notificationId = 10086
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("1", "hello", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        return ForegroundInfo(notificationId, createNotification())
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("标题")
            .setContentText("内容")
            .build()
    }
}

////////////////////////////////////////////////////

class MyCoroutineWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notificationId = 10087
        return ForegroundInfo(notificationId, createNotification())
    }

    override suspend fun doWork(): Result {
        Log.e(TAG, "线程：${Thread.currentThread().name}")
        return Result.success()
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("标题")
            .setContentText("内容")
            .build()
    }
}

////////////////////////////////////////////////////

class DataWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val delay = inputData.getLong("delay", 0L)
        Thread.sleep(delay)
        Log.e(TAG, "获取数据：${inputData}")
        return Result.success(inputData)
    }
}

class CacheWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "缓存数据：${inputData}")
        return Result.success(inputData)
    }
}

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "上传数据：${inputData}")
        return Result.success()
    }
}
