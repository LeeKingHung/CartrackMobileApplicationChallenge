package com.lkh012349s.androidcodingchallenge.models

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.lkh012349s.androidcodingchallenge.models.daos.UserDao

@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
	
	abstract fun userDao(): UserDao
	
	companion object {
		
		@Volatile private var INSTANCE: MyDatabase? = null
		
		fun getInstance(context: Context): MyDatabase = INSTANCE ?: synchronized(this) {
			
			buildDatabase(context).also {
				INSTANCE = it
			}
			
		}
		
		private fun buildDatabase(context: Context) = Room.databaseBuilder(
			context.applicationContext, MyDatabase::class.java, "Database.db"
		).build()
		
	}
	
}