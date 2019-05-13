package com.lkh012349s.androidcodingchallenge.views

import android.graphics.PorterDuff
import android.widget.Spinner

fun Spinner.setBackgroundColorFilter(color: Int) {
	background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}