package co.pacastrillonp.pruebadeingreso.persistence.mappers

import co.pacastrillonp.pruebadeingreso.model.network.Address
import co.pacastrillonp.pruebadeingreso.model.network.Company
import co.pacastrillonp.pruebadeingreso.model.network.Geo
import co.pacastrillonp.pruebadeingreso.model.network.UserResponse
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import org.junit.Assert
import org.junit.Test

class UserResponseToUserEntityMapperTest {

    @Test
    fun `Should return correct user entity when all values in user response are defined`() {

        val userResponse = UserResponse(
            id = 1,
            name = "Leanne Graham",
            username = " Bret ",
            email = "Sincere @april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998 - 3874",
                geo = Geo(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )

        val expected = UserEntity(
            1,
            "Leanne Graham",
            "1-770-736-8031 x56442",
            "Sincere @april.biz",
        )

        val result = userResponse.userResponseToUserEntityMapper()

        Assert.assertEquals(expected, result)
    }

}