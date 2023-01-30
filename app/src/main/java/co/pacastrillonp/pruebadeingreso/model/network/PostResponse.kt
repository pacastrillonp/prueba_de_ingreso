package co.pacastrillonp.pruebadeingreso.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)