package com.karnilovich.daggertestapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.view.activity.RegistrationActivity
import com.karnilovich.daggertestapp.view.presenter.RegistrationFragmentPresenter
import com.karnilovich.daggertestapp.view.presenter.TosFragmentPresenter
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_tos.*
import javax.inject.Inject

class TosFragment : Fragment(R.layout.fragment_tos), TosFragmentView {

    @Inject
    lateinit var presenter: TosFragmentPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)

        btn_accept.setOnClickListener {
            presenter.acceptTos((activity as RegistrationActivity).getCurrentUserId())
        }
    }

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

    override fun toMainActivity() {
        (activity as RegistrationActivity).toMainActivity()
    }

    override fun showToast(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_LONG).show()
    }
}