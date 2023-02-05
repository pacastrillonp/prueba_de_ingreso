package co.pacastrillonp.pruebadeingreso.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.mapper.userEntityToUserPresentableMapper
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import co.pacastrillonp.pruebadeingreso.persistence.dao.UserDao
import co.pacastrillonp.pruebadeingreso.persistence.mappers.userResponseToUserEntityMapper
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val networkRepository: NetworkRepository,
    private val userDao: UserDao
) : ViewModel() {

    var localUserPresentable = emptyList<UserPresentable>()

    val users: LiveData<List<UserEntity>> by lazy { userDao.getAllUsers() }

     val _fetchingData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val fetchingData: LiveData<Boolean> get() = _fetchingData

    private val _userPresentable: MutableLiveData<List<UserPresentable>> by lazy {
        MutableLiveData<List<UserPresentable>>(listOf())
    }

    val userPresentable: LiveData<List<UserPresentable>> get() = _userPresentable

    fun updateUserPresentable(userEntity: List<UserEntity>) {
        localUserPresentable = userEntity.map { it.userEntityToUserPresentableMapper() }
        _userPresentable.postValue(localUserPresentable)
    }

    fun fetchUserData() {
       if (_fetchingData.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _fetchingData.postValue(true)
                val result = networkRepository.fetchUsers()
                if (result.isSuccessful) {
                    result.body()?.let {
                        it.map { userResponse ->
                            userDao.insert(userResponse.userResponseToUserEntityMapper())
                        }
                    }
                    _fetchingData.postValue(false)
                }
            }
        }
    }

    fun searchedItems(searchBy: String) {
        _userPresentable.postValue(localUserPresentable.filter { userPresentable ->
            userPresentable.name.contains(
                searchBy
            )
        })
    }
}