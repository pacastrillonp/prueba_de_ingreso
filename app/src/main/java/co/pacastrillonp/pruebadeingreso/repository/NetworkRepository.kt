package co.pacastrillonp.pruebadeingreso.repository

import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import co.pacastrillonp.pruebadeingreso.network.ApiProvider
import retrofit2.Response

interface NetworkRepository {
    suspend fun fetchUsers(): Response<List<UserResponse>>
    suspend fun getPostById(userId: Int): Response<List<PostResponse>>
}

class DefaultNetworkRepository(private val apiProvider: ApiProvider) :
    NetworkRepository {

    override suspend fun fetchUsers(): Response<List<UserResponse>>
     =    apiProvider.getUsers()

    override suspend fun getPostById(userId: Int): Response<List<PostResponse>> {
        return apiProvider.getPostById(userId)
    }
}