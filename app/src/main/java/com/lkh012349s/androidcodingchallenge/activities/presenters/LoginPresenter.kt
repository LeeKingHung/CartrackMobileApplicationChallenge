package com.lkh012349s.androidcodingchallenge.activities.presenters

import android.content.Context
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.interfaces.LoginInterface
import com.lkh012349s.androidcodingchallenge.models.DataOperation
import com.lkh012349s.androidcodingchallenge.models.User
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
class LoginPresenter {
	
	@RootContext protected lateinit var context: Context
	@Bean protected lateinit var dataOperation: DataOperation
	internal lateinit var view: LoginInterface
	
	fun populateDefaultUser() {
		view.populateDefaultUser(User.getDefaultUser())
	}
	
	fun login(username: String, password: String, country: String) {
		
		if(
			
			showErrorIfEmpty(username, R.string.username)
			
			&& showErrorIfEmpty(password, R.string.password)
			
			&& showErrorIfEmpty(country, R.string.country)
		
		) {
			
			dataOperation.login(username, password, country) { isSuccessful ->
				if(isSuccessful) view.onSuccessfulLogin()
				else view.showError(getString(R.string.msg_error_invalid_user_credentials))
			}
			
		}
		
	}
	
	/**
	 * @return true if no error.
	 */
	fun showErrorIfEmpty(s: String, fieldRes: Int): Boolean {
		
		if(s.isEmpty()) {
			val field = context.getString(fieldRes)
			view.showError(String.format(getString(R.string.msg_enter), field))
			return false
		}
		
		return true
		
	}
	
	private fun getString(res: Int): String {
		return context.getString(res)
	}
	
}