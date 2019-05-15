package com.lkh012349s.androidcodingchallenge.activities

import android.annotation.SuppressLint
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.interfaces.LoginInterface
import com.lkh012349s.androidcodingchallenge.activities.presenters.LoginPresenter
import com.lkh012349s.androidcodingchallenge.models.User
import com.lkh012349s.androidcodingchallenge.views.getTextOrEmpty
import com.lkh012349s.androidcodingchallenge.views.showErrorMsg
import com.lkh012349s.androidcodingchallenge.views.underline
import com.ybs.countrypicker.CountryPicker
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.*
import org.androidannotations.annotations.res.ColorRes
import org.androidannotations.annotations.res.StringRes

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
class LoginActivity : AppCompatActivity(), LoginInterface {
	
	companion object {
		const val COUNTRY_PICKER_TAG = "COUNTRY_PICKER"
	}

	// AndroidAnnotations injection: the fields below will be auto injected.
	@Bean protected lateinit var presenter: LoginPresenter
	@ColorRes(R.color.colorAccent) @JvmField protected final var colorAccent = 0
	@StringRes(R.string.title_select_country) protected lateinit var titleSelectCountry: String

	private var selectedCountry = ""

	// AndroidAnnotations injection: this method will run automatically after fields injections.
	@AfterInject
	fun afterInject() {
		presenter.view = this
	}

	// AndroidAnnotations injection: this method will run automatically after views injections.
	@AfterViews
	fun afterViewInjections() {
		textViewCountry.underline()
		animate()
		presenter.populateDefaultUser()
	}

	// AndroidAnnotations: this method will run in UI thread
	@UiThread(propagation = UiThread.Propagation.REUSE, delay = 200)
	fun animate() {
		TransitionManager.beginDelayedTransition(layoutRoot)
		imageViewLogo.visibility = View.VISIBLE
		showLoginSection()
	}

	// AndroidAnnotations: this method will run in UI thread
	@UiThread(propagation = UiThread.Propagation.REUSE, delay = 1000)
	fun showLoginSection() {
		TransitionManager.beginDelayedTransition(layoutRoot)
		cardViewLoginSection.visibility = View.VISIBLE
	}

	// AndroidAnnotations: this method will generate view on-click listener.
	@Click(R.id.textViewCountry)
	fun onCountryPickerClick() {
		
		val picker = CountryPicker.newInstance(titleSelectCountry)
		
		picker.setListener { name, _, _, _ ->
			setCountry(name)
			picker.dismiss()
		}
		
		picker.show(supportFragmentManager, COUNTRY_PICKER_TAG)
		
	}

	// AndroidAnnotations: this method will generate view on-click listener.
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
		showErrorMsg(msg, layoutRoot)
	}
	
	override fun onSuccessfulLogin() {
		MainActivitiy_.intent(this).start()
		finish()
	}
	
	//endregion
	
}