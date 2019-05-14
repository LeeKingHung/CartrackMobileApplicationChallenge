package com.lkh012349s.androidcodingchallenge.views

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.card.MaterialCardView
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.MapActivity_
import com.lkh012349s.androidcodingchallenge.models.Address
import com.lkh012349s.androidcodingchallenge.models.UserInfo
import kotlinx.android.synthetic.main.view_user_card.view.*
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.res.ColorRes
import org.androidannotations.annotations.res.DimensionRes

@EViewGroup(R.layout.view_user_card)
class UserCardView(context: Context) : MaterialCardView(context) {
	
	@ColorRes(R.color.colorAccent) @JvmField final var colorAccent = 0
	@DimensionRes(R.dimen.card_elevation) @JvmField final var cardElevationValue = 0f
	private var curAddress: Address? = null
	
	@AfterInject
	fun afterInject() {
		setCardBackgroundColor(colorAccent)
		cardElevation = cardElevationValue
	}
	
	@AfterViews
	fun afterViewInjections() {
		textViewUsername.setDrawableLeft(R.drawable.user)
		textViewEmail.setDrawableLeft(R.drawable.email)
		textViewPhone.setDrawableLeft(R.drawable.phone)
		textViewWebsite.setDrawableLeft(R.drawable.website)
		textViewAddress.setDrawableLeft(R.drawable.address)
		textViewCompany.setDrawableLeft(R.drawable.company)
		textViewMap.underline()
	}
	
	@Click(R.id.textViewMap)
	fun onMapClick() {
		MapActivity_.intent(context).address(curAddress!!).start()
	}
	
	@SuppressLint("SetTextI18n")
	fun set(userInfo: UserInfo) {
		textViewNameId.text = "${userInfo.name} (id: ${userInfo.id})"
		textViewUsername.text = userInfo.username
		textViewEmail.text = userInfo.email
		textViewPhone.text = userInfo.phone
		textViewWebsite.text = userInfo.website
		curAddress = userInfo.address
		textViewAddress.text = curAddress!!.toString()
		val company = userInfo.company
		textViewCompany.text = company.name
		textViewCompanyCatchPhrase.text = company.catchPhrase
		textViewCompanyBs.text = company.bs
	}
	
}