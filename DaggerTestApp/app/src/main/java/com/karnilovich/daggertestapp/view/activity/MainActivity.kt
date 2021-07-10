package com.karnilovich.daggertestapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karnilovich.daggertestapp.MyApplication
import com.karnilovich.daggertestapp.R
import com.karnilovich.daggertestapp.database.entity.Note
import com.karnilovich.daggertestapp.view.NotesAdapter
import com.karnilovich.daggertestapp.view.base.BaseActivity
import com.karnilovich.daggertestapp.view.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private var notesAdapter: NotesAdapter? = null
    private var btnLogout: Button? = null
    private var btnAdd: Button? = null
    private var rwNotes: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val userDataStore = (application as MyApplication).appComponent.userDataStore()
        userDataStore.getLoggedUserComponent()!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout = findViewById(R.id.btn_logout)
        btnLogout?.setOnClickListener { presenter.logout() }
        btnAdd = findViewById(R.id.btn_add)
        btnAdd?.setOnClickListener { toAddNoteActivity() }

        rwNotes = findViewById(R.id.rw_notes)
        rwNotes?.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(emptyList())
        rwNotes?.adapter = notesAdapter

        presenter.bind(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.requestNotes()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun toLoginActivity() {
        val intent = Intent(applicationContext, LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun toAddNoteActivity() {
        val intent = Intent(applicationContext, AddNoteActivity::class.java)
        startActivity(intent)
    }

    override fun setNotes(notes: List<Note>) {
        notesAdapter?.setNotes(notes)
    }

    override fun showToast(textId: Int) {
        Toast.makeText(this, getString(textId), Toast.LENGTH_LONG).show()
    }

}