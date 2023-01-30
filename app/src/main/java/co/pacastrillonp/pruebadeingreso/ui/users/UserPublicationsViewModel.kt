package co.pacastrillonp.pruebadeingreso.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable
import co.pacastrillonp.pruebadeingreso.persistence.mappers.postResponseToUserPublicationsMapper
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPublicationsViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _fetchingData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val fetchingData: LiveData<Boolean> get() = _fetchingData

    private val _publications: MutableLiveData<List<UserPublicationsPresentable>> by lazy {
        MutableLiveData<List<UserPublicationsPresentable>>(listOf())
    }

    val publications: LiveData<List<UserPublicationsPresentable>> get() = _publications

    fun fetchUserPublications(
        userId: Int,
        name: String,
        telephoneNumber: String,
        emailAddress: String
    ) {

        _fetchingData.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            val result = networkRepository.getPostById(userId)
            if (result.isSuccessful) {
                _fetchingData.postValue(false)
                result.body()?.let {
                    _publications.postValue(
                        it.map { postResponse ->
                            postResponse.postResponseToUserPublicationsMapper(
                                name,
                                telephoneNumber,
                                emailAddress
                            )
                        }
                    )
                }
            }
        }
    }
}