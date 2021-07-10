package com.karnilovich.daggertestapp.view.activity

import androidx.annotation.StringRes
import com.karnilovich.daggertestapp.view.base.BaseView

interface AddNoteView: BaseView {

    fun close()

    fun showToast(@StringRes textId: Int)
}