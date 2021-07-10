package com.karnilovich.daggertestapp.view.fragment

import androidx.annotation.StringRes
import com.karnilovich.daggertestapp.view.base.BaseView

interface TosFragmentView: BaseView {

    fun toMainActivity()
    fun showToast(@StringRes textId: Int)
}