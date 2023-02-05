package co.pacastrillonp.pruebadeingreso.network

import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiProvider {

    @GET(Endpoint.USERS)
    fun getUsers(): Single<List<UserResponse>>

    @GET(Endpoint.POTS)
    suspend fun getPostById(
        @Query("userId") userId: Int
    ): Response<List<PostResponse>>

}