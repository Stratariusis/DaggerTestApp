package com.karnilovich.daggertestapp.view.presenter

import com.karnilovich.daggertestapp.Constants
import com.karnilovich.daggertestapp.view.activity.RegistrationView
import com.karnilovich.daggertestapp.view.base.BasePresenter
import javax.inject.Inject

class RegistrationPresenter @Inject constructor() : BasePresenter<RegistrationView>() {

    override fun bind(view: RegistrationView) {
        super.bind(view)
        if (view.getCurrentUserId() == Constants.NULLABLE_USER_ID){
            view.showRegistrationFragment(false)
        } else {
            view.showTosFragment(false)
        }
    }

}