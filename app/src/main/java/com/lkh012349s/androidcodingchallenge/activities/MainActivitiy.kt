package com.lkh012349s.androidcodingchallenge.activities

import android.annotation.SuppressLint
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lkh012349s.androidcodingchallenge.R
import com.lkh012349s.androidcodingchallenge.activities.interfaces.MainInterface
import com.lkh012349s.androidcodingchallenge.activities.presenters.MainPresenter
import com.lkh012349s.androidcodingchallenge.models.UserInfo
import com.lkh012349s.androidcodingchallenge.views.MyAdapter
import com.lkh012349s.androidcodingchallenge.views.showErrorMsg
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.res.DimensionPixelSizeRes

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
class MainActivitiy : AppCompatActivity(), MainInterface {
	
	@Bean protected lateinit var presenter: MainPresenter
	@DimensionPixelSizeRes(R.dimen.spacing) @JvmField final var mSpacing = 0
	lateinit var mAdapter: MyAdapter
	
	internal inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
		
		override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
			outRect.bottom = verticalSpaceHeight
		}
		
	}
	
	@AfterInject
	fun afterInject() {
		presenter.view = this
	}
	
	@AfterViews
	fun afterViewInjections() {
		setSupportActionBar(toolbar)
		setupRecyclerView()
		presenter.loadUsers()
	}
	
	fun setupRecyclerView() {
		recyclerView.setHasFixedSize(true)
		val layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		recyclerView.addItemDecoration(VerticalSpaceItemDecoration(mSpacing))
		mAdapter = MyAdapter()
		recyclerView.adapter = mAdapter
	}
	
	// =============================================================================================================
	// region MainInterface Implementation
	// =============================================================================================================
	
	override fun loadUsers(userInfos: List<UserInfo>?) {
		if(userInfos == null) showErrorMsg(getString(R.string.msg_error_unable_to_load_users), recyclerView)
		else mAdapter.setData(userInfos)
	}
	
}