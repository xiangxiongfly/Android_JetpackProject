package com.example.viewbindingdemo.encapsulation.delegate

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB : ViewBinding> Fragment.viewBindings(
    noinline factory: (View) -> VB = { view ->
        VB::class.java.getMethod("bind", View::class.java)
            .invoke(null, view) as VB
    }
) = FragmentViewBindingDelegate(factory)

class FragmentViewBindingDelegate<VB : ViewBinding>(
    private val factory: (View) -> VB,
) : ReadOnlyProperty<Fragment, VB> {
    private var viewBinding: VB? = null

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        viewBinding?.let { return it }

        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        viewBinding = factory(thisRef.requireView())
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                "TAG",
                "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. The instance of viewBinding will be not cached."
            )
        } else {
            thisRef.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    thisRef.lifecycle.removeObserver(this)
                    // Lifecycle的ON_DESTROY是在Fragment的onDestroyView()之前执行
                    // 因此需要将主线程中的所有操作执行完后才能ViewBinding
                    mainHandler.post {
                        viewBinding = null
                    }
                }
            })
        }
        return viewBinding!!
    }
}