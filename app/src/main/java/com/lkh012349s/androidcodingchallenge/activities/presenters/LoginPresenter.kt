package com.lkh012349s.androidcodingchallenge.activities.presenters

import com.lkh012349s.androidcodingchallenge.activities.interfaces.LoginInterface
import com.lkh012349s.androidcodingchallenge.models.DataOperation
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
class LoginPresenter {
	
	@Bean protected lateinit var dataOperation: DataOperation
	internal lateinit var loginInterface: LoginInterface
	
}