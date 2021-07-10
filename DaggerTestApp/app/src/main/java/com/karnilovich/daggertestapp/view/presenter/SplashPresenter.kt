package com.karnilovich.daggertestapp.view.presenter

import android.util.Log
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.domain.GetLoggedUserIdInteractor
import com.karnilovich.daggertestapp.domain.GetUserByIdInteractor
import com.karnilovich.daggertestapp.domain.IsUserLoggedInInteractor
import com.karnilovich.daggertestapp.view.activity.SplashView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val isUserLoggedInInteractor: IsUserLoggedInInteractor,
    private val getUserByIdInteractor: GetUserByIdInteractor,
    private val getLoggedUserIdInteractor: GetLoggedUserIdInteractor
) : BasePresenter<SplashView>() {

    override fun bind(view: SplashView) {
        super.bind(view)

        checkIsUserLoggedIn()
    }

    private fun checkIsUserLoggedIn(){
        isUserLoggedInInteractor.execute()
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    checkIsTosAccepted()
                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )
    }

    private fun checkIsTosAccepted(){
        getLoggedUserIdInteractor.execute()
            .flatMap { getUserByIdInteractor.execute(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if (it.isTosAccepted) {
                        getView()?.toMainActivity()
                    } else {
                        getView()?.toRegistrationActivity(it.userId)
                    }
                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )
    }

}