package co.pacastrillonp.pruebadeingreso.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String? = null,
    val telephoneNumber: String? = null,
    val emailAddress: String? = null
) {
    companion object {
        const val TABLE_NAME = "user_table"
    }
}