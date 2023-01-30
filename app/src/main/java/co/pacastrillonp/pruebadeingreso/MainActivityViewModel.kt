package co.pacastrillonp.pruebadeingreso

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.mapper.userEntityToUserPresentable
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var localUserPresentable = emptyList<UserPresentable>()

    val users: LiveData<List<UserEntity>> by lazy { networkRepository.getUsers() }

    private val _fetchingData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val fetchingData: LiveData<Boolean> get() = _fetchingData

    private val _userPresentable: MutableLiveData<List<UserPresentable>> by lazy {
        MutableLiveData<List<UserPresentable>>(listOf())
    }

    val userPresentable: LiveData<List<UserPresentable>> get() = _userPresentable

    fun updateUserPresentable(userEntity: List<UserEntity>) {
        localUserPresentable = userEntity.map { it.userEntityToUserPresentable() }
        _userPresentable.postValue(localUserPresentable)
    }

    fun fetchUserData() {
        if (_fetchingData.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _fetchingData.postValue(true)
                networkRepository.fetchUsers()
                _fetchingData.postValue(false)
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