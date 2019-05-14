package com.lkh012349s.androidcodingchallenge.views

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.lkh012349s.androidcodingchallenge.models.UserInfo

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	class UserInfoViewHolder(var view: UserCardView) : RecyclerView.ViewHolder(view)
	
	class ProgressViewHolder(var view: View) : RecyclerView.ViewHolder(view)
	
	companion object {
		private const val VIEW_TYPE_LOADING = 0
		private const val VIEW_TYPE_NORMAL = 1
	}
	
	private var dataSet: ArrayList<UserInfo>? = null
	private var isLoading = false
	
	fun isLoading(): Boolean {
		return isLoading
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		
		if(viewType == VIEW_TYPE_LOADING) {
			val view = View.inflate(parent.context, com.lkh012349s.androidcodingchallenge.R.layout.view_progress_bar, null)
			setLayoutParams(view)
			return ProgressViewHolder(view)
		}
		
		val view = UserCardView_.build(parent.context)
		setLayoutParams(view)
		return UserInfoViewHolder(view)
		
	}
	
	private fun setLayoutParams(view: View) {
		val layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
		view.layoutParams = layoutParams
	}
	
	override fun getItemCount(): Int {
		return if(dataSet == null) 0 else (if(isLoading) dataSet!!.size + 1 else dataSet!!.size)
	}
	
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if(holder is UserInfoViewHolder) holder.view.set(dataSet!![position])
	}
	
	fun add(dataSet: List<UserInfo>) {
		
		if(this.dataSet == null) {
			
			this.dataSet = arrayListOf()
			this.dataSet!!.addAll(dataSet)
			notifyDataSetChanged()
			
		} else {
			
			isLoading = true
			notifyDataSetChanged()
			
			val runnable = Runnable {
				isLoading = false
				this.dataSet!!.addAll(dataSet)
				notifyDataSetChanged()
			}
			
			Handler().postDelayed(runnable, 1000)
			
		}
		
	}
	
	override fun getItemViewType(position: Int): Int {
		
		return if(isLoading) {
			if(position == itemCount - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
		} else {
			VIEW_TYPE_NORMAL
		}
		
	}
	
}