package com.lkh012349s.androidcodingchallenge.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.interfaces.LoginInterface
import com.lkh012349s.androidcodingchallenge.activities.presenters.LoginPresenter
import com.lkh012349s.androidcodingchallenge.views.setBackgroundColorFilter
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
class LoginActivity : AppCompatActivity(), LoginInterface {
	
	@Bean lateinit var presenter: LoginPresenter
	
	@AfterViews
	fun afterViewInjections() {
		presenter.loginInterface = this
		spinner.setBackgroundColorFilter(Color.WHITE)
		animate()
	}
	
	@UiThread(propagation = UiThread.Propagation.REUSE, delay = 200)
	fun animate() {
		TransitionManager.beginDelayedTransition(layoutRoot)
		imageViewLogo.visibility = View.VISIBLE
		showLoginSection()
	}
	
	@UiThread(propagation = UiThread.Propagation.REUSE, delay = 1000)
	fun showLoginSection() {
		TransitionManager.beginDelayedTransition(layoutRoot)
		cardViewLoginSection.visibility = View.VISIBLE
	}
	
	// =============================================================================================================
	// region LoginInterface Implementation
	// =============================================================================================================
	
	//endregion
	
}