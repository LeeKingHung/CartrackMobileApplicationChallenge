package com.lkh012349s.androidcodingchallenge.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
	
	@PrimaryKey val username: String,
	
	@ColumnInfo val password: String,
	
	@ColumnInfo val country: String

){

}