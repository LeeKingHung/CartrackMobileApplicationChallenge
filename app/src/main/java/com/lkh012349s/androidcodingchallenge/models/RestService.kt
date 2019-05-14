package com.lkh012349s.androidcodingchallenge.models

import retrofit2.Call
import retrofit2.http.GET

interface RestService {
	
	@GET("users")
	fun getUserInfos(): Call<List<UserInfo>>
	
}