package com.lkh012349s.androidcodingchallenge.activities.presenters

import android.content.Context
import com.lkh012349s.androidcodingchallenge.activities.interfaces.MainInterface
import com.lkh012349s.androidcodingchallenge.models.DataOperation
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
class MainPresenter {
	
	@RootContext protected lateinit var context: Context
	@Bean protected lateinit var dataOperation: DataOperation
	internal lateinit var view: MainInterface
	
}