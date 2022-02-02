package com.rrohaill.refactoringsample.data

import com.google.gson.annotations.SerializedName

data class Place (
	@SerializedName("alias") val alias : String,
	@SerializedName("name") val name : String,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("latitude") val latitude : Double,
	@SerializedName("description") val description : String,
	@SerializedName("icon") val icon : String
)