package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.domain.RegistrationInteractor
import com.karnilovich.daggertestapp.view.base.BasePresenter
import com.karnilovich.daggertestapp.view.fragment.RegistrationFragmentView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RegistrationFragmentPresenter @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) : BasePresenter<RegistrationFragmentView>() {

    fun performRegistration(login: String, password: String) {
        registrationInteractor.execute(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if (it.isTosAccepted) {
                        getView()?.toMainActivity()
                    } else {
                        getView()?.showTosFragment(false)
                    }

                },
                onError = {
                    getView()?.showToast(R.string.something_went_wrong)
                }
            )

    }
}