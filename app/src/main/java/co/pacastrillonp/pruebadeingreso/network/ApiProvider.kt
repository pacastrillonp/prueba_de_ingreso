package co.pacastrillonp.pruebadeingreso.network

import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiProvider {

    @GET(Endpoint.USERS)
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET(Endpoint.POTS)
    suspend fun getPostById(
        @Query("userId") userId: Int
    ): Response<List<PostResponse>>

}