package co.pacastrillonp.pruebadeingreso.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import co.pacastrillonp.pruebadeingreso.persistence.dao.UserDao

@Database(
    version = TechnicalTestRoomDataBase.v01,
    entities = [UserEntity::class]
)

abstract class TechnicalTestRoomDataBase : RoomDatabase() {

    companion object {
        const val v01 = 1
    }

    abstract fun userDao(): UserDao

}