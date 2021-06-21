package com.durand.helper.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Creates a one off view model factory for the given view model instance.
 */
object ViewModelUtil {

    @Suppress("UNCHECKED_CAST")
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
            }

}