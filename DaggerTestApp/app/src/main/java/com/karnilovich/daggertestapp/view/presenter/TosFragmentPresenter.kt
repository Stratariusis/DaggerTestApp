package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.data.store.throwable.NoSuchUserException
import com.karnilovich.daggertestapp.domain.AcceptTosInteractor
import com.karnilovich.daggertestapp.view.activity.RegistrationView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import com.karnilovich.daggertestapp.view.fragment.TosFragmentView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TosFragmentPresenter @Inject constructor(private val acceptTosInteractor: AcceptTosInteractor) :
    BasePresenter<TosFragmentView>() {

    fun acceptTos(userId: Long) {
        acceptTosInteractor.execute(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    getView()?.toMainActivity()
                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )

    }

}