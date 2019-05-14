package com.lkh012349s.androidcodingchallenge.views

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lkh012349s.androidcodingchallenge.activities.presenters.MainPresenter.Companion.PAGE_SIZE

abstract class PaginationScrollListener(private var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
	
	abstract val isLastPage: Boolean
	
	abstract val isLoading: Boolean
	
	protected abstract fun loadMoreItems()
	
	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		
		super.onScrolled(recyclerView, dx, dy)
		val visibleItemCount = layoutManager.childCount
		val totalItemCount = layoutManager.itemCount
		val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
		
		if(!isLoading && !isLastPage && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
			
			&& totalItemCount >= PAGE_SIZE
		
		) loadMoreItems()
		
	}
	
}

