package com.lkh012349s.androidcodingchallenge.models

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lkh012349s.androidcodingchallenge.models.daos.UserDao

@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
	
	abstract fun userDao(): UserDao
	
}