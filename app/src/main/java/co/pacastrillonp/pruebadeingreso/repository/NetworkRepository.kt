package co.pacastrillonp.pruebadeingreso.repository

import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import co.pacastrillonp.pruebadeingreso.network.ApiProvider
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface NetworkRepository {
    suspend fun getPostById(userId: Int): Response<List<PostResponse>>
    fun getUsers(): Single<List<UserResponse>>
}

class DefaultNetworkRepository(private val apiProvider: ApiProvider) :
    NetworkRepository {

    override suspend fun getPostById(userId: Int): Response<List<PostResponse>> {
        return apiProvider.getPostById(userId)
    }

    override fun getUsers(): Single<List<UserResponse>> = apiProvider.getUsers()
}