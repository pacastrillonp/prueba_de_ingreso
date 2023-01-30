package co.pacastrillonp.pruebadeingreso.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} ORDER BY id DESC")
    fun getAllUsers(): LiveData<List<UserEntity>>
}