package kr.swkang.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kr.swkang.core.data.PokeRepository
import kr.swkang.core.data.PokeRepositoryImpl
import kr.swkang.network.retrofit.poke.PokeApi

/**
 * @author bmo
 * @since 2023-03-27
 */
@Module
@InstallIn(SingletonComponent::class)
object PokeRepositoryModule {
    @Singleton
    @Provides
    fun providePokeRepositoy(
        pokeApi: PokeApi
    ): PokeRepository = PokeRepositoryImpl(pokeApi)
}
