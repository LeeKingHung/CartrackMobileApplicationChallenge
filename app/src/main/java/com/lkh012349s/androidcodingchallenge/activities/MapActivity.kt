package com.lkh012349s.androidcodingchallenge.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.lkh012349s.androidcodingchallenge.models.Address
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra

@SuppressLint("Registered")
@EActivity(com.lkh012349s.androidcodingchallenge.R.layout.activity_map)
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

	// AndroidAnnotations injection: the field below will be auto injected.
	// It is the data passed from another activity A when starting this activity from A.
	@Extra lateinit var address: Address

	// AndroidAnnotations injection: this method will run automatically after views injections.
	@AfterViews
	fun afterViewInjections() {
		val fragment =
			supportFragmentManager.findFragmentById(com.lkh012349s.androidcodingchallenge.R.id.fragment) as SupportMapFragment
		fragment.getMapAsync(this)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		val latLng = address.geo.toLatLng()
		googleMap.addMarker(MarkerOptions().position(latLng).title(address.toString()))
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
	}

}