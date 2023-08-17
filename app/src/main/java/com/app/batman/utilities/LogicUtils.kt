package com.app.batman.utilities

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity


object LogicUtils {

    fun onBackPressed(activity: FragmentActivity, function: () -> Any) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    function()
                }
            }
        activity.onBackPressedDispatcher.addCallback(activity, callback)
    }
}