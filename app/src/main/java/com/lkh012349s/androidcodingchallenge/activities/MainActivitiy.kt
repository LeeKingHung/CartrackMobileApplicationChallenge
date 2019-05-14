package com.lkh012349s.androidcodingchallenge.activities

import android.support.v7.app.AppCompatActivity
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.presenters.MainPresenter
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
class MainActivitiy : AppCompatActivity() {
	
	@Bean protected lateinit var presenter: MainPresenter
	
}