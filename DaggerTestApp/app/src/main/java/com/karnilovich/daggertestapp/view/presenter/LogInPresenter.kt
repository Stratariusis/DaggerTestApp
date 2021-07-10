package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.data.store.throwable.NoSuchUserException
import com.karnilovich.daggertestapp.domain.LoginInteractor
import com.karnilovich.daggertestapp.view.activity.LogInView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LogInPresenter @Inject constructor(private val loginInteractor: LoginInteractor) : BasePresenter<LogInView>() {

    fun performLogin(login: String, password: String){
        loginInteractor.execute(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.isTosAccepted){
                        getView()?.toMainActivity()
                    } else {
                        getView()?.toRegistrationActivity(it.userId)
                    }

                },
                onError = {
                    if(it is NoSuchUserException){
                        getView()?.showToast(R.string.there_is_no_such_user)
                    } else {
                        getView()?.showToast(R.string.something_went_wrong)
                    }
                }
            )
    }

}