package com.lkh012349s.androidcodingchallenge.activities

import android.annotation.SuppressLint
import android.graphics.Paint
import android.support.design.widget.Snackbar
import android.support.design.widget.SnackbarContentLayout
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.interfaces.LoginInterface
import com.lkh012349s.androidcodingchallenge.activities.presenters.LoginPresenter
import com.lkh012349s.androidcodingchallenge.models.User
import com.lkh012349s.androidcodingchallenge.views.getTextOrEmpty
import com.ybs.countrypicker.CountryPicker
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread
import org.androidannotations.annotations.res.ColorRes
import org.androidannotations.annotations.res.StringRes

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
class LoginActivity : AppCompatActivity(), LoginInterface {
	
	companion object {
		const val COUNTRY_PICKER_TAG = "COUNTRY_PICKER"
	}
	
	@Bean protected lateinit var presenter: LoginPresenter
	@ColorRes(R.color.colorAccent) @JvmField protected final var colorAccent = 0
	@StringRes(R.string.title_select_country) protected lateinit var titleSelectCountry: String
	private var selectedCountry = ""
	
	@AfterViews
	fun afterViewInjections() {
		presenter.view = this
		textViewCountry.paintFlags = textViewCountry.paintFlags or Paint.UNDERLINE_TEXT_FLAG  // underline textview
		animate()
		presenter.populateDefaultUser()
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
	
	@Click(R.id.textViewCountry)
	fun onCountryPickerClick() {
		
		val picker = CountryPicker.newInstance(titleSelectCountry)
		
		picker.setListener { name, _, _, _ ->
			setCountry(name)
			picker.dismiss()
		}
		
		picker.show(supportFragmentManager, COUNTRY_PICKER_TAG)
		
	}
	
	@Click(R.id.button)
	fun onLoginButtonClick() {
		val username = editTextUsername.getTextOrEmpty()
		val password = editTextPassword.getTextOrEmpty()
		presenter.login(username, password, selectedCountry)
	}
	
	fun setCountry(country: String) {
		textViewCountry.text = country
		selectedCountry = country
	}
	
	// =============================================================================================================
	// region LoginInterface Implementation
	// =============================================================================================================
	
	override fun populateDefaultUser(user: User) {
		editTextUsername.setText(user.username)
		editTextPassword.setText(user.password)
		setCountry(user.country)
	}
	
	override fun showError(msg: String) {
		val snackbar = Snackbar.make(layoutRoot, msg, Snackbar.LENGTH_SHORT)
		val contentLayout = (snackbar.view as ViewGroup).getChildAt(0) as SnackbarContentLayout
		val tv = contentLayout.findViewById(android.support.design.R.id.snackbar_text) as TextView
		tv.setTextColor(colorAccent)
		snackbar.show()
	}
	
	override fun onSuccessfulLogin() {
		MainActivitiy_.intent(this).start()
		finish()
	}
	
	//endregion
	
}