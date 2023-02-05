package co.pacastrillonp.pruebadeingreso.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.mapper.userEntityToUserPresentableMapper
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import co.pacastrillonp.pruebadeingreso.persistence.dao.UserDao
import co.pacastrillonp.pruebadeingreso.persistence.mappers.userResponseToUserEntityMapper
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import co.pacastrillonp.pruebadeingreso.util.subscribeOnComputationThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivityViewModel(
    private val networkRepository: NetworkRepository,
    private val userDao: UserDao
) : ViewModel() {

    lateinit var disposable: Disposable

    private var localUserPresentable = emptyList<UserPresentable>()

    val userPresentable: MutableLiveData<List<UserPresentable>> by lazy {
        MutableLiveData<List<UserPresentable>>(
            emptyList()
        )
    }

    val fetchingData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val fetchUsers: Observable<List<UserPresentable>> =
        userDao.getAllUsersRx()
            .subscribeOnComputationThread()
            .map { it.map { userEntity -> userEntity.userEntityToUserPresentableMapper() } }
            .distinctUntilChanged()
            .toObservable()
            .doOnNext {
                localUserPresentable = it
            }

    fun fetchData() {
        fetchingData.postValue(true)
        networkRepository.getUsers().toObservable()
            .subscribe(object : Observer<List<UserResponse>> {
                override fun onComplete() {}

                override fun onError(e: Throwable) {}

                override fun onNext(usersResponse: List<UserResponse>) {
                    fetchingData.postValue(false)
                    userDao.insert(usersResponse.map { it.userResponseToUserEntityMapper() })
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }
            })
    }

    fun updateUserPresentable(users: List<UserPresentable>) {
        localUserPresentable = users
        userPresentable.postValue(localUserPresentable)
    }

    fun searchedItems(searchBy: String) {
        userPresentable.postValue(localUserPresentable.filter { userPresentable ->
            userPresentable.name.contains(
                searchBy
            )
        })
    }
}