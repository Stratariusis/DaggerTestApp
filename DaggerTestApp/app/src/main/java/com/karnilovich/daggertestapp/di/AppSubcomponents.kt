package com.karnilovich.daggertestapp.di

import dagger.Module

@Module(
    subcomponents = [
        LogInComponent::class,
        RegistrationComponent::class,
        LoggedUserComponent::class
    ]
)
class AppSubcomponents