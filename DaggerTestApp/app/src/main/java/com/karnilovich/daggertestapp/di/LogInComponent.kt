package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.view.activity.LogInActivity
import dagger.Subcomponent

@LogInScope
@Subcomponent(modules = [UserLoginModule::class])
interface LogInComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LogInComponent
    }

    fun inject(activity: LogInActivity)
}