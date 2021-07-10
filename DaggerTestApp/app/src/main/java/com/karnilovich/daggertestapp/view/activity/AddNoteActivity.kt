package com.karnilovich.daggertestapp.view.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.karnilovich.daggertestapp.MyApplication
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.view.base.BaseActivity
import com.karnilovich.daggertestapp.view.presenter.AddNotePresenter
import javax.inject.Inject

class AddNoteActivity: BaseActivity(), AddNoteView {

    @Inject
    lateinit var presenter: AddNotePresenter

    private var etTitle: EditText? = null
    private var etText: EditText? = null
    private var btnAddNote: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val userDataStore = (application as MyApplication).appComponent.userDataStore()
        userDataStore.getLoggedUserComponent()!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        etTitle = findViewById(R.id.et_title)
        etText = findViewById(R.id.et_text)
        btnAddNote = findViewById(R.id.btn_add)
        btnAddNote?.setOnClickListener {
            presenter.addNote(
                etTitle?.text.toString(),
                etText?.text.toString()
            )
        }

        presenter.bind(this)
    }

    override fun close() {
        finish()
    }

    override fun showToast(textId: Int) {
        Toast.makeText(this, getString(textId), Toast.LENGTH_LONG).show()
    }
}