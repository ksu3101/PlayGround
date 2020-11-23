package com.swkang.playground.base.di.network

import com.swkang.model.domain.covid19.repository.Covid19Repository
import com.swkang.playground.repository.covid19.Covid19KrApi
import com.swkang.playground.repository.covid19.Covid19RepositoryImpl
import com.swkang.playground.repository.covid19.Covid19WorldApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Covid19NetworkModule {

    @Singleton
    @Provides
    @Corona19KrServer
    fun provideCorona19KrRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://api.corona-19.kr/")
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideCorona19KrApi(@Corona19KrServer retrofit: Retrofit): Covid19KrApi =
        retrofit.create(Covid19KrApi::class.java)

    @Singleton
    @Provides
    @Covid19WorldServer
    fun provideCovid19WorldRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://api.covid19api.com/")
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideCovid19WorldApi(@Covid19WorldServer retrofit: Retrofit): Covid19WorldApi =
        retrofit.create(Covid19WorldApi::class.java)

    @Singleton
    @Provides
    fun provideCovid19Repository(
        covid19KrApi: Covid19KrApi,
        covid19WorldApi: Covid19WorldApi
    ): Covid19Repository {
        return Covid19RepositoryImpl(covid19KrApi, covid19WorldApi)
    }
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Corona19KrServer

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Covid19WorldServer