package com.lkh012349s.androidcodingchallenge.models

import android.content.Context
import com.lkh012349s.androidcodingchallenge.models.daos.UserDao
import org.androidannotations.annotations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@EBean
class DataOperation {
	
	companion object {
		const val BASE_URL = "https://jsonplaceholder.typicode.com"
	}

	// AndroidAnnotations injection: the field below will be auto injected.
	@RootContext lateinit var context: Context

	private var userDao: UserDao? = null
	private var restService: RestService? = null

	// AndroidAnnotations injection: this method will run automatically after fields injections.
	@AfterInject
	fun afterInject() {
		val database = MyDatabase.getInstance(context)
		userDao = database.userDao()
	}
	
	fun getUserDao(): UserDao = userDao ?: synchronized(this) {
		val database = MyDatabase.getInstance(context)
		database.userDao()
	}
	
	fun getRestService(): RestService = restService ?: synchronized(this) {
		val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build()
		retrofit.create(RestService::class.java)
	}
	
	fun loadUsers(runnable: (List<UserInfo>?) -> Unit) {
		
		val restService = getRestService()
		val call = restService.getUserInfos()
		
		call.enqueue(object : Callback<List<UserInfo>?> {
			
			override fun onFailure(call: Call<List<UserInfo>?>, t: Throwable) {
				runnable(null)
			}
			
			override fun onResponse(call: Call<List<UserInfo>?>, response: Response<List<UserInfo>?>) {
				runnable(response.body())
			}
			
		})
		
	}
	
	fun login(username: String, password: String, country: String, runnable: (isSuccessful: Boolean) -> Unit) {
		
		runBackground {
			
			val userDao = getUserDao()
			var users = userDao.getAllUsers()
			
			if(users.isEmpty()) {
				createUserTable()
				users = userDao.getAllUsers()
			}
			
			for(user in users) {
				
				if(user.username == username && user.password == password && user.country == country) {
					runOnUiThread { runnable(true) }
					return@runBackground
				}
				
			}
			
			runOnUiThread { runnable(false) }
			
		}
		
	}
	
	fun createUserTable() {
		getUserDao().insertUser(User.getDefaultUser())
	}
	
	/**
	 * AndroidAnnotations: Runnable will run in background thread.
	 */
	@Background
	protected fun runBackground(runnable: () -> Unit) {
		runnable()
	}
	
	/**
	 * AndroidAnnotations: Runnable will run in UI thread.
	 */
	@UiThread(propagation = UiThread.Propagation.REUSE)
	protected fun runOnUiThread(runnable: () -> Unit) {
		runnable()
	}
	
}