package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.data.store.throwable.NoSuchUserException
import com.karnilovich.daggertestapp.domain.GetNotesInteractor
import com.karnilovich.daggertestapp.domain.LogOutInteractor
import com.karnilovich.daggertestapp.view.activity.MainView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val logOutInteractor: LogOutInteractor,
    private val getNotesInteractor: GetNotesInteractor
): BasePresenter<MainView>() {

    fun requestNotes(){
        getNotesInteractor.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    getView()?.setNotes(it)
                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )
    }

    fun logout(){
        logOutInteractor.execute()
        getView()?.toLoginActivity()
    }

}