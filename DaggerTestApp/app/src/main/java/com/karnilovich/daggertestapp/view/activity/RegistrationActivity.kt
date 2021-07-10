package com.karnilovich.daggertestapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.karnilovich.daggertestapp.Constants
import com.karnilovich.daggertestapp.MyApplication
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.di.RegistrationComponent
import com.karnilovich.daggertestapp.view.base.BaseActivity
import com.karnilovich.daggertestapp.view.fragment.RegistrationFragment
import com.karnilovich.daggertestapp.view.fragment.TosFragment
import com.karnilovich.daggertestapp.view.presenter.RegistrationPresenter
import javax.inject.Inject

class RegistrationActivity: BaseActivity(), RegistrationView {

    @Inject
    lateinit var presenter: RegistrationPresenter

    lateinit var registrationComponent: RegistrationComponent

    private var userId: Long = Constants.NULLABLE_USER_ID

    private var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        registrationComponent = (application as MyApplication).appComponent.registrationComponent().create()
        registrationComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        container = findViewById(R.id.container)

        userId = intent.getLongExtra(Constants.USER_ID, Constants.NULLABLE_USER_ID)
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

    override fun showRegistrationFragment(isBackStack: Boolean) {
        openFragment(RegistrationFragment(), isBackStack)
    }

    override fun showTosFragment(isBackStack: Boolean) {
        openFragment(TosFragment(), isBackStack)
    }

    override fun getCurrentUserId(): Long {
        return userId
    }

    private fun openFragment(fragment: Fragment?, isBackStack: Boolean) {
        try {
            val transaction = supportFragmentManager.beginTransaction()
            if (fragment != null) {
                transaction.replace(
                    R.id.container,
                    fragment,
                    fragment.javaClass.simpleName
                )
                if (isBackStack) {
                    transaction.addToBackStack(null)
                }
                transaction.commitAllowingStateLoss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}