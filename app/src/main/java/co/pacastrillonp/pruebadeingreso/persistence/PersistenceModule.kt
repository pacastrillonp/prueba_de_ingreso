package co.pacastrillonp.pruebadeingreso.persistence

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(
                androidContext(),
                TechnicalTestRoomDataBase::class.java,
                "TechnicalTestRoomDataBase"
            )
            .build()
    }

    single { get<TechnicalTestRoomDataBase>().userDao() }
}