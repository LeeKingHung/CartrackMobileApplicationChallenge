package com.lkh012349s.androidcodingchallenge.views

import android.widget.TextView

fun TextView.getTextOrEmpty(): String {
	return text?.toString() ?: ""
}