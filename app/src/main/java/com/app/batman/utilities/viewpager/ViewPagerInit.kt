package com.app.batman.utilities.viewpager

import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.app.batman.R


class ViewPagerInit private constructor(
    private var clipToPadding: Boolean = false,
    private var clipChildren: Boolean = false,
    private var offscreenPageLimit: Int = 0,
) {

    data class Builder(
        var clipToPadding: Boolean = false,
        var clipChildren: Boolean = false,
        var offscreenPageLimit: Int? = 0,
    ) {
        fun clipToPadding(value: Boolean): Builder = apply { this.clipToPadding = value }
        fun clipChildren(value: Boolean): Builder = apply { this.clipChildren = value }
        fun offscreenPageLimit(value: Int): Builder = apply { this.offscreenPageLimit = value }

        fun build() =
            ViewPagerInit(clipToPadding, clipChildren, offscreenPageLimit!!)
    }

    fun ViewPager2.show() {
        with(this) {
            clipToPadding = this@ViewPagerInit.clipToPadding
            clipChildren = this@ViewPagerInit.clipChildren
            offscreenPageLimit = this@ViewPagerInit.offscreenPageLimit
        }
        this.viewPagerUiInit()
    }

    private fun ViewPager2.viewPagerUiInit() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        this.setPageTransformer { page, position ->
            val viewPager2 = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager2.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL)
                    page.translationX = -offset
                else
                    page.translationX = offset

            } else {
                page.translationY = offset
            }
        }
    }
}