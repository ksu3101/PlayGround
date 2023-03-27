package kr.swkang.network.retrofit.poke.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kr.swkang.network.retrofit.poke.PokeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author beemo
 * @since 2023/03/15
 */
@Module
@InstallIn(SingletonComponent::class)
object PokeNetworkModule {
    @Singleton
    @Provides
    fun providePokeRetrofit(
        okHttpClient: OkHttpClient
    ): PokeApi = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokeapi.co/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(PokeApi::class.java)
}
