package com.swkang.playground.base.di

import android.content.Context
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.playground.base.helper.MessageHelperImpl
import com.swkang.playground.base.helper.ResourceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideMessageHelper(@ApplicationContext context: Context): MessageHelper =
        MessageHelperImpl(context)

    @Provides
    fun provideResourceHelper(@ApplicationContext context: Context): ResourceHelper =
        ResourceHelperImpl(context)

}