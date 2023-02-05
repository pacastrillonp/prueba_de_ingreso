package co.pacastrillonp.pruebadeingreso.model.mapper

import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import org.junit.Assert
import org.junit.Test

class UserEntityToUserPresentableMapperTest {

    @Test
    fun `Should return correct user presentable when all values in user entity are defined`() {
        val userEntity =
            UserEntity(1, "Leanne Graham", "1-770-736-8031 x56442", "Sincere @april.biz")

        val expected = UserPresentable(
            1, "Leanne Graham", "1-770-736-8031 x56442", "Sincere @april.biz"
        )

        val result = userEntity.userEntityToUserPresentableMapper()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Should return correct user presentable when user entity are not defined correctly`() {
        val userEntity =
            UserEntity(1, null, null, null)

        val expected = UserPresentable(
            1, "", "", ""
        )

        val result = userEntity.userEntityToUserPresentableMapper()

        Assert.assertEquals(expected, result)
    }

}