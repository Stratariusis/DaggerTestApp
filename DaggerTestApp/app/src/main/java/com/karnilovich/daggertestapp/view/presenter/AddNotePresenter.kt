package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.data.store.throwable.NoSuchUserException
import com.karnilovich.daggertestapp.domain.AddNoteInteractor
import com.karnilovich.daggertestapp.view.activity.AddNoteView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddNotePresenter @Inject constructor(
    private val addNoteInteractor: AddNoteInteractor
): BasePresenter<AddNoteView>() {

    fun addNote(title: String, text: String){
        addNoteInteractor.execute(title, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    getView()?.showToast(R.string.note_has_been_added)
                    getView()?.close()
                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )
    }

}