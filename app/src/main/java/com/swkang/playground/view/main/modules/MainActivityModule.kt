package com.swkang.playground.view.main.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.swkang.model.domain.main.nav.MainNavigationHelper
import com.swkang.playground.view.main.nav.MainNavigationHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @ActivityScoped
    @Provides
    fun provideMainNavigationHelper(
        @ActivityContext context: Context
    ): MainNavigationHelper {
        return MainNavigationHelperImpl(context as AppCompatActivity)
    }

}