package com.swkang.playground.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T: ViewBinding>(
    val fragment: Fragment,
    val viewBinder: (View) -> T
): ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object: DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveData =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer
                    viewLifecycleOwner.lifecycle.addObserver(object: DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveData)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveData)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) return binding

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            // Fragment가 이미 destory상태인 경우,
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }
        return viewBinder(thisRef.requireView()).also { this.binding = it }
    }
}

// 확장 함수
fun <T : ViewBinding> Fragment.viewBinding(viewBinder: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBinder)