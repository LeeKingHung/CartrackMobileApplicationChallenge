package com.lkh012349s.androidcodingchallenge.models

import android.content.Context
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
class DataOperation {
	
	@RootContext lateinit var context: Context
	
}