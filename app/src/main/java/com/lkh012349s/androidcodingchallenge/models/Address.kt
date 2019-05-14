package com.lkh012349s.androidcodingchallenge.models

import java.io.Serializable

data class Address(
	
	val street: String,
	
	val suite: String,
	
	val city: String,
	
	val zipcode: String,
	
	val geo: Geo

) : Serializable {
	
	constructor() : this("", "", "", "", Geo())
	
	override fun toString(): String {
		return "$suite $street\n$city $zipcode"
	}
	
}