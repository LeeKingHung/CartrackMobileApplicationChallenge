package com.lkh012349s.androidcodingchallenge.models

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class Geo(val lat: Double, val lng: Double) : Serializable {
	
	constructor() : this(0.0, 0.0)
	
	fun toLatLng(): LatLng = LatLng(lat, lng)
	
}