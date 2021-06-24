package com.swkang.playground.view.googlebilling.module

import android.app.Activity
import android.content.Context
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.playground.view.googlebilling.GoogleBillingHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
class GoogleBillingModule {

    @Provides
    @FragmentScoped
    fun provideGoogleBillingHelper(
        @ActivityContext context: Context
    ): GoogleBillingHelper {
        return GoogleBillingHelperImpl(context as Activity)
    }

}