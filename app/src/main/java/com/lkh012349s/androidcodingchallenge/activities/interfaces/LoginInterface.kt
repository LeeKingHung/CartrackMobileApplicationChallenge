package com.lkh012349s.androidcodingchallenge.activities.interfaces

import com.lkh012349s.androidcodingchallenge.models.User

interface LoginInterface {
	
	fun showError(msg: String)
	
	fun onSuccessfulLogin()
	
	fun populateDefaultUser(user: User)
	
}