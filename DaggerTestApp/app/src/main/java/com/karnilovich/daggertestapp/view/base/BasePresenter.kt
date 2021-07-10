package com.karnilovich.daggertestapp.view.base

import java.util.*

open class BasePresenter<V : BaseView> {

    private var view: V? = null

    open fun bind(view: V) {
        this.view = view
    }

    open fun unbind() {
        view = null
    }

    protected open fun getView(): V? {
        return view
    }

}