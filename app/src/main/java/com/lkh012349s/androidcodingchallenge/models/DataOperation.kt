package com.lkh012349s.androidcodingchallenge.models

import android.content.Context
import com.lkh012349s.androidcodingchallenge.models.daos.UserDao
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext
import org.androidannotations.annotations.UiThread

@EBean
class DataOperation {
	
	@RootContext lateinit var context: Context
	private lateinit var userDao: UserDao
	
	@AfterInject
	fun afterInject() {
		val database = MyDatabase.getInstance(context)
		userDao = database.userDao()
	}
	
	fun login(username: String, password: String, country: String, runnable: (isSuccessful: Boolean) -> Unit) {
		
		runBackground {
			
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
		userDao.insertUser(User.getDefaultUser())
	}
	
	/**
	 * Runnable will run in background thread.
	 */
	@Background
	fun runBackground(runnable: () -> Unit) {
		runnable()
	}
	
	/**
	 * Runnable will run in UI thread.
	 */
	@UiThread(propagation = UiThread.Propagation.REUSE)
	fun runOnUiThread(runnable: () -> Unit) {
		runnable()
	}
	
}