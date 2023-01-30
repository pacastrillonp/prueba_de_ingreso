package co.pacastrillonp.pruebadeingreso.repository

import androidx.lifecycle.LiveData
import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import co.pacastrillonp.pruebadeingreso.network.ApiProvider
import co.pacastrillonp.pruebadeingreso.persistence.dao.UserDao
import co.pacastrillonp.pruebadeingreso.persistence.mappers.userResponseToUserEntityMapper
import kotlinx.coroutines.delay
import retrofit2.Response

interface NetworkRepository {
    suspend fun fetchUsers()
    fun getUsers(): LiveData<List<UserEntity>>
    suspend fun getPost(): LiveData<List<PostResponse>>
    suspend fun getPostById(userId: Int): Response<List<PostResponse>>
}

class DefaultNetworkRepository(private val apiProvider: ApiProvider, private val userDao: UserDao) :
    NetworkRepository {

    override suspend fun fetchUsers() {
        delay(3000)
        apiProvider.getUsers().map {
            userDao.insert(it.userResponseToUserEntityMapper())
        }
    }

    override fun getUsers(): LiveData<List<UserEntity>> = userDao.getAllUsers()

    override suspend fun getPost(): LiveData<List<PostResponse>> {
        delay(3000)
        return apiProvider.getPost()
    }

    override suspend fun getPostById(userId: Int): Response<List<PostResponse>> {
        return apiProvider.getPostById(userId)
    }
}