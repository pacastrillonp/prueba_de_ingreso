package co.pacastrillonp.pruebadeingreso.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import co.pacastrillonp.pruebadeingreso.persistence.dao.UserDao
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import co.pacastrillonp.pruebadeingreso.ui.main.MainActivityViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Flowable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class MainActivityViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val userEntity = listOf(
        UserEntity(
            1,
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz"
        )
    )

    private val expectedPresentable = listOf(
        UserPresentable(
            1,
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz"
        )
    )

    private val networkRepository = mock<NetworkRepository>()
    private val userDao = mock<UserDao>() {
        on { getAllUsersRx() } doReturn Flowable.just(userEntity)
    }

    private val underTest = MainActivityViewModel(
        networkRepository,
        userDao
    )

    @Test
    fun `Should return correct value userPresentable`() {
        val mutableLiveData = underTest.userPresentable
        mutableLiveData.postValue(expectedPresentable)
        assertEquals(expectedPresentable, mutableLiveData.value)
    }

    @Test
    fun `Should return correct value fetchingData`() {
        val mutableLiveData = underTest.fetchingData
        mutableLiveData.postValue(true)
        assertTrue(mutableLiveData.value!!)
    }

    @Test
    fun `Should return user presentable when fetch data from dao`() {

        whenever(userDao.getAllUsersRx()) doReturn Flowable.just(userEntity)

        val testObserver = underTest.fetchUsers.test()

        testObserver.awaitCount(1)

        testObserver.assertValues(expectedPresentable)
    }

    @Test
    fun `Should not return user presentable when no data from dao`() {

        whenever(userDao.getAllUsersRx()) doReturn Flowable.never()

        val testObserver = underTest.fetchUsers.test()

        testObserver.assertNoValues()
    }
}