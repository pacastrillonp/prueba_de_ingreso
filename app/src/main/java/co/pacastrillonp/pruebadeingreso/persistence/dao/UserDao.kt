package co.pacastrillonp.pruebadeingreso.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.pacastrillonp.pruebadeingreso.model.persistence.UserEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: List<UserEntity>)

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} ORDER BY id DESC")
    fun getAllUsersRx(): Flowable<List<UserEntity>>
}