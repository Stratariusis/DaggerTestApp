package com.karnilovich.daggertestapp.di

import com.karnilovich.daggertestapp.view.activity.AddNoteActivity
import com.karnilovich.daggertestapp.view.activity.MainActivity
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent(modules = [UseLoggedModule::class])
interface LoggedUserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoggedUserComponent
    }

    fun inject(activity: MainActivity)

    fun inject(activity: AddNoteActivity)
}