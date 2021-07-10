package com.karnilovich.daggertestapp.view.activity

import androidx.annotation.StringRes
import com.karnilovich.daggertestapp.view.base.BaseView

interface LogInView : BaseView {

    fun toMainActivity()

    fun toRegistrationActivity(userId: Long?)

    fun showToast(@StringRes textId: Int)
}