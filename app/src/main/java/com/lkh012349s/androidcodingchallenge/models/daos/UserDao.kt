package com.lkh012349s.androidcodingchallenge.models.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.lkh012349s.androidcodingchallenge.models.User
import io.reactivex.Completable

@Dao
interface UserDao {
	
	@Query("SELECT * FROM User")
	fun getAllUsers(): List<User>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertUser(user: User): Completable
	
}