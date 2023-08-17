package com.app.batman.utilities

import android.view.View
import io.supercharge.shimmerlayout.ShimmerLayout

class ShimmerLoadingInit(
    private val shimmerLayout: ShimmerLayout,
    private val view: View,

    ) {
    fun startShimmer() {
        view.visibility = View.INVISIBLE
        shimmerLayout.startShimmerAnimation()
        shimmerLayout.visibility = View.VISIBLE
    }

    fun stopShimmer() {
        view.visibility = View.VISIBLE
        shimmerLayout.stopShimmerAnimation()
        shimmerLayout.visibility = View.GONE
    }
}