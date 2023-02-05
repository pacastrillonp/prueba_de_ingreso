package co.pacastrillonp.pruebadeingreso.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable
import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import co.pacastrillonp.pruebadeingreso.repository.NetworkRepository
import co.pacastrillonp.pruebadeingreso.ui.users.UserPublicationsViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response
import kotlin.test.assertTrue

class UserPublicationsViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val expectedPresentable = listOf(
        UserPublicationsPresentable(
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz",
            "ut dicta possimus sint mollitia voluptas commodi quo doloremque"
        )
    )

    private val networkRepository = mock<NetworkRepository>()

    private val underTest = UserPublicationsViewModel(
        networkRepository
    )

    @Test
    fun `Should return correct value userPresentable`() {
        val mutableLiveData = underTest.publications
        mutableLiveData.postValue(expectedPresentable)
        Assert.assertEquals(expectedPresentable, mutableLiveData.value)
    }

    @Test
    fun `Should return correct value fetchingData`() {
        val mutableLiveData = underTest.fetchingData
        mutableLiveData.postValue(true)
        assertTrue(mutableLiveData.value!!)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Should return correct value fetchUserPublications`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        `when`(networkRepository.getPostById(1)).thenReturn(
            Response.success(
                listOf(
                    PostResponse(
                        "ut dicta possimus sint mollitia voluptas commodi quo doloremque",
                        1,
                        "Leanne Graham",
                        1
                    )
                )
            )
        )

        try {
            underTest.fetchUserPublications(
                1,
                "Leanne Graham",
                "1-770-736-8031 x56442",
                "Sincere @april.biz",
            )
            assertTrue(underTest.fetchingData.value!!)
        } finally {
            Dispatchers.resetMain()
        }
    }

}