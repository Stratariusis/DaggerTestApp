package com.karnilovich.daggertestapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.karnilovich.daggertestapp.Constants
import com.karnilovich.daggertestapp.MyApplication
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.view.base.BaseActivity
import com.karnilovich.daggertestapp.view.presenter.LogInPresenter
import javax.inject.Inject

class LogInActivity : BaseActivity(), LogInView {

    @Inject
    lateinit var presenter: LogInPresenter

    private var etLogin: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnRegistration: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.logInComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLogin = findViewById(R.id.et_login)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_log_in)
        btnLogin?.setOnClickListener {
            presenter.performLogin(
                etLogin?.text.toString(),
                etPassword?.text.toString()
            )
        }
        btnRegistration = findViewById(R.id.btn_registration)
        btnRegistration?.setOnClickListener { toRegistrationActivity(null) }

        presenter.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun toMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun toRegistrationActivity(userId: Long?) {
        val intent = Intent(applicationContext, RegistrationActivity::class.java)
        if (userId != null) {
            intent.putExtra(Constants.USER_ID, userId)
        }
        startActivity(intent)
    }

    override fun showToast(textId: Int) {
        Toast.makeText(this, getString(textId), Toast.LENGTH_LONG).show()
    }

}