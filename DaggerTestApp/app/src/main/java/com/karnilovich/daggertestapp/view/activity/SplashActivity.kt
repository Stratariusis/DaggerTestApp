package com.karnilovich.daggertestapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.karnilovich.daggertestapp.Constants
import com.karnilovich.daggertestapp.MyApplication
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.view.base.BaseActivity
import com.karnilovich.daggertestapp.view.presenter.SplashPresenter
import javax.inject.Inject


class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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

    override fun toLogInActivity() {
        val intent = Intent(applicationContext, LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun toRegistrationActivity(userId: Long?) {
        val intent = Intent(applicationContext, RegistrationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        if (userId != null) {
            intent.putExtra(Constants.USER_ID, userId)
        }
        startActivity(intent)
    }

    override fun showToast(textId: Int) {
        Toast.makeText(applicationContext, textId, Toast.LENGTH_LONG).show()
    }
}