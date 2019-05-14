package com.lkh012349s.androidcodingchallenge.models

data class Company(
	
	val name: String,
	
	val catchPhrase: String,
	
	val bs: String

) {
	
	constructor() : this("", "", "")
	
}