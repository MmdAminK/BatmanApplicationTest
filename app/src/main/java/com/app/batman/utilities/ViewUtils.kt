package com.app.batman.utilities

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.TextView
import com.app.batman.R
import com.app.batman.core.ErrorHandler
import com.google.android.material.snackbar.Snackbar

object ViewUtils {

    @JvmOverloads
    fun View.showInternetWarningToast(msg: String = ErrorHandler.networkProblem) {
        this.makeSnackBar(msg)
    }

    @SuppressLint("ResourceType")
    fun View.makeSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
        val snackBar = Snackbar.make(this, message, length)
        val layout = snackBar.view as Snackbar.SnackbarLayout
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layout.setBackgroundColor(this.resources.getColor(R.color.pink400, null))
        }
        val snackText =
            layout.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            snackText.setTextColor(this.resources.getColor(R.color.white, null))
        }
        snackBar.show()
    }
}