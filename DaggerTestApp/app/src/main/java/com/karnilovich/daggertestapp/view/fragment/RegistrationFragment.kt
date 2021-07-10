package com.karnilovich.daggertestapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.view.activity.RegistrationActivity
import com.karnilovich.daggertestapp.view.presenter.RegistrationFragmentPresenter
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration), RegistrationFragmentView {

    @Inject
    lateinit var presenter: RegistrationFragmentPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)

        btn_registration.setOnClickListener {
            presenter.performRegistration(
                et_login?.text.toString(),
                et_password?.text.toString()
            )
        }

    }

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

    override fun showTosFragment(isBackStack: Boolean) {
        (activity as RegistrationActivity).showTosFragment(isBackStack)
    }

    override fun toMainActivity() {
        (activity as RegistrationActivity).toMainActivity()
    }

    override fun showToast(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_LONG).show()
    }
}