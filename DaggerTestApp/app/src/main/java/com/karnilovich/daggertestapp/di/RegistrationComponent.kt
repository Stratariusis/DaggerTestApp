package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.view.activity.RegistrationActivity
import com.karnilovich.daggertestapp.view.fragment.RegistrationFragment
import com.karnilovich.daggertestapp.view.fragment.TosFragment
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(modules = [UserRegistrationModule::class])
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegistrationActivity)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: TosFragment)
}