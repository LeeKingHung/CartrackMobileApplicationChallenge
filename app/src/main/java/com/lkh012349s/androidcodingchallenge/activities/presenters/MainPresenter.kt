package com.lkh012349s.androidcodingchallenge.activities.presenters

import android.content.Context
import com.lkh012349s.androidcodingchallenge.activities.interfaces.MainInterface
import com.lkh012349s.androidcodingchallenge.models.DataOperation
import com.lkh012349s.androidcodingchallenge.models.UserInfo
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
class MainPresenter {
	
	companion object {
		const val PAGE_SIZE = 4
	}

	// AndroidAnnotations injection: the fields below will be auto injected.
	@RootContext protected lateinit var context: Context
	@Bean protected lateinit var dataOperation: DataOperation

	internal lateinit var view: MainInterface
	private var allUserInfos: List<UserInfo>? = null
	
	fun loadUsers() {
		
		dataOperation.loadUsers { userInfos ->
			allUserInfos = userInfos
			view.loadMoreUsers()
		}
		
	}
	
	fun loadMoreUser(lastItemIndex: Int): Triple<Int, Boolean, List<UserInfo>?> {

		if(allUserInfos == null) return Triple(-1, true, null)
		val size = allUserInfos!!.size

		// Current index is already the last index
		if(lastItemIndex + 1 == size) return Triple(lastItemIndex, true, listOf())

		// The remaining items count is <= page size
		if(lastItemIndex + PAGE_SIZE >= size - 1) return Triple(size - 1, true, allUserInfos!!.slice((lastItemIndex + 1) until size))

		return Triple(lastItemIndex + PAGE_SIZE, false, allUserInfos!!.slice((lastItemIndex + 1)..(lastItemIndex + PAGE_SIZE)))

	}
	
}