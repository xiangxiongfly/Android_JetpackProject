package com.example.myapplication.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myapplication.exceptions.coroutineExceptionHandler
import kotlinx.coroutines.*

/**
 * 协程扩展
 */
inline fun AppCompatActivity.requestMain(noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun AppCompatActivity.requestIO(noinline block: suspend CoroutineScope.() -> Unit): Job {
    return lifecycleScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun AppCompatActivity.delayMain(
    delayTime: Long,
    noinline block: suspend CoroutineScope.() -> Unit,
) {
    lifecycleScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}

inline fun Fragment.requestMain(noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun Fragment.requestIO(noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun Fragment.delayMain(
    delayTime: Long,
    noinline block: suspend CoroutineScope.() -> Unit,
) {
    lifecycleScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}

inline fun ViewModel.requestMain(noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun ViewModel.requestIO(noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        block.invoke(this)
    }
}

inline fun ViewModel.delayMain(
    delayTime: Long,
    noinline block: suspend CoroutineScope.() -> Unit,
) {
    viewModelScope.launch(coroutineExceptionHandler) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}

inline fun LifecycleCoroutineScope.requestMain(noinline block: suspend CoroutineScope.() -> Unit) {
    launch(coroutineExceptionHandler) {
        block.invoke(this)
    }
}
