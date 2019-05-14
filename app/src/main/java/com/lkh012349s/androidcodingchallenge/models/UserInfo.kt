package com.lkh012349s.androidcodingchallenge.models

data class UserInfo(
	
	val id: Long,
	
	val name: String,
	
	val username: String,
	
	val email: String,
	
	val address: Address,
	
	val phone: String,
	
	val website: String,
	
	val company: Company

) {
	
	@Suppress("unused")
	constructor() : this(0, "", "", "", Address(), "", "", Company())
	
}