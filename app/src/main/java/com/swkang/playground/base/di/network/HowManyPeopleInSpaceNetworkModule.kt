package com.swkang.playground.base.di.network

import com.swkang.model.domain.peopleinspace.repository.HowManyPeopleInSpaceRepository
import com.swkang.playground.repository.peopleinspace.HowManyPeopleInSpaceApi
import com.swkang.playground.repository.peopleinspace.HowManyPeopleInSpaceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object HowManyPeopleInSpaceNetworkModule {

    @HowManyPeopleInSpaceServer
    @Provides
    fun provideHowManyPeopleInSpaceRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://api.open-notify.org/")
            .client(okHttpClient)
            .build()

    @Provides
    fun provideHowManyPeopleInSpaceApi(@HowManyPeopleInSpaceServer retrofit: Retrofit): HowManyPeopleInSpaceApi =
        retrofit.create(HowManyPeopleInSpaceApi::class.java)

    @Provides
    fun provideHowManyPeopleInSpaceRepository(
        howManyPeopleInSpaceApi: HowManyPeopleInSpaceApi
    ): HowManyPeopleInSpaceRepository {
        return HowManyPeopleInSpaceRepositoryImpl(howManyPeopleInSpaceApi)
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HowManyPeopleInSpaceServer