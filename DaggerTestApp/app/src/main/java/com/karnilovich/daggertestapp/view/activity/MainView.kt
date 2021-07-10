package com.karnilovich.daggertestapp.view.activity

import androidx.annotation.StringRes
import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.view.base.BaseView

interface MainView: BaseView {

    fun toLoginActivity()

    fun toAddNoteActivity()

    fun setNotes(notes: List<Note>)

    fun showToast(@StringRes textId: Int)

}