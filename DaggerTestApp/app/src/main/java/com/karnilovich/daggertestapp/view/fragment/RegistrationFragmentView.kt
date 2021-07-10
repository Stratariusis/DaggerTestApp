package com.karnilovich.daggertestapp.view.fragment

import androidx.annotation.StringRes
import com.karnilovich.daggertestapp.view.base.BaseView

interface RegistrationFragmentView: BaseView {

    fun showTosFragment(isBackStack: Boolean)
    fun toMainActivity()
    fun showToast(@StringRes textId: Int)
}