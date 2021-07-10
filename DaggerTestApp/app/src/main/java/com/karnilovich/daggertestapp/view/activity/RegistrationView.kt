package com.karnilovich.daggertestapp.view.activity

import com.karnilovich.daggertestapp.view.base.BaseView

interface RegistrationView : BaseView {

    fun toMainActivity()

    fun showRegistrationFragment(isBackStack: Boolean)

    fun showTosFragment(isBackStack: Boolean)

    fun getCurrentUserId(): Long

}