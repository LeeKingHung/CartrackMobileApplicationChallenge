package com.lkh012349s.androidcodingchallenge.views

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.support.design.widget.Snackbar
import android.support.design.widget.SnackbarContentLayout
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lkh012349s.androidcodingchallenge.R

fun TextView.getTextOrEmpty(): String {
	return text?.toString() ?: ""
}

fun TextView.setDrawableLeft(res: Int) {
	var drawable = ResourcesCompat.getDrawable(resources, res, null)
	val bitmap = (drawable as BitmapDrawable).bitmap
	val size = (textSize * 1.5).toInt()
	drawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, size, size, true))
	drawable.setBounds(0, 0, size, size)
	setCompoundDrawables(drawable, null, null, null)
}

fun TextView.underline() {
	paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun Activity.showErrorMsg(msg: String, view: View) {
	val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
	val contentLayout = (snackbar.view as ViewGroup).getChildAt(0) as SnackbarContentLayout
	val tv = contentLayout.findViewById(android.support.design.R.id.snackbar_text) as TextView
	tv.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
	snackbar.show()
}