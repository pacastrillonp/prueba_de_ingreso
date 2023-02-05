package co.pacastrillonp.pruebadeingreso.network

import co.pacastrillonp.pruebadeingreso.enviroment.Constants
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.Api.BASE_URL)
            .addConverterFactory(get<Converter.Factory>())
            .addCallAdapterFactory(get<CallAdapter.Factory>())
            .build()
    }

    single<Converter.Factory> { GsonConverterFactory.create() }

    single<ApiProvider> { get<Retrofit>().create(ApiProvider::class.java) }

    single<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }
}