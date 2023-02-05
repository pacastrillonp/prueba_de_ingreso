package co.pacastrillonp.pruebadeingreso.persistence.mappers

import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable
import co.pacastrillonp.pruebadeingreso.model.network.PostResponse
import org.junit.Assert
import org.junit.Test

class PostResponseToUserPublicationsMapperTest {

    @Test
    fun `Should return correct user publications presentable when all values in post response are defined`() {

        val postResponse = PostResponse(
            userId = 1,
            id = 11,
            title = "et ea vero quia laudantium autem",
            body = "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus"
        )

        val expected = UserPublicationsPresentable(
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz",
            "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus"
        )

        val result = postResponse.postResponseToUserPublicationsMapper(
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz",
        )

        Assert.assertEquals(expected, result)
    }

}

