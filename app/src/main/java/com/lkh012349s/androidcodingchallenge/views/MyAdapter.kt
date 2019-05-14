package com.lkh012349s.androidcodingchallenge.views

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lkh012349s.androidcodingchallenge.models.UserInfo

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
	
	class MyViewHolder(var view: UserCardView) : RecyclerView.ViewHolder(view)
	
	private var dataSet: List<UserInfo>? = null
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val view = UserCardView_.build(parent.context)
		val layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
		view.layoutParams = layoutParams
		return MyViewHolder(view)
	}
	
	override fun getItemCount(): Int {
		return dataSet?.size ?: 0
	}
	
	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.view.set(dataSet!![position])
	}
	
	fun setData(dataSet: List<UserInfo>) {
		this.dataSet = dataSet
		notifyDataSetChanged()
	}
	
}