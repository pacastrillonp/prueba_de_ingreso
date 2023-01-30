package co.pacastrillonp.pruebadeingreso.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geo(
    val lat: String,
    val lng: String
)