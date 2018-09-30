package com.example.roman.testproject.ui.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.ui.search.adapter.PersonAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject
import android.widget.EditText
import com.example.roman.testproject.ui.BaseActivity
import com.example.roman.testproject.ui.Config
import com.example.roman.testproject.ui.PdfReaderActivity


class SearchPersonActivity : SearchPersonContract.View, BaseActivity() {

    @Inject
    override lateinit var presenter: SearchPersonContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        initView()
    }

    private fun initView() {
        search_button.setOnClickListener {
            presenter.initSearch(query_edit.text.toString())
        }
        result_list.layoutManager = LinearLayoutManager(this)
        result_list.adapter = PersonAdapter { personInfo: PersonInfo, s: String ->
            when (s) {
                Config.FAVOURITE_BUTTON_PRESSED ->  showCreateCategoryDialog(personInfo)
                Config.FAVOURITE_BUTTON_DEFAULT -> presenter.deleteFromFavourite(personInfo.id!!)
                Config.PDF_BUTTON_PRESSED -> {
                    val intent = Intent(this, PdfReaderActivity::class.java)
                    intent.putExtra("key",personInfo.linkPDF)
                    startActivity(intent)
                }
            }
        }
    }

    fun showCreateCategoryDialog(personInfo: PersonInfo) {
        val builder = AlertDialog.Builder(this@SearchPersonActivity)
        val input = EditText(this)
        builder.setTitle("Add comment for this Person")
        builder.setView(input)
        builder.setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->
            personInfo.comment = input.text.toString()
            presenter.addToFavourite(personInfo)
        }
        builder.setNegativeButton("Cancel"){ dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_search
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_search
    }

    override fun showPersonInfo(listPerson: List<PersonInfo>) {
        (result_list.adapter as PersonAdapter).updateList(listPerson)
    }

    override fun showError() {

    }

    override fun successAdd() {
        Toast.makeText(this,"Person add to favourite",Toast.LENGTH_SHORT).show()
    }

    override fun sucessDelete() {
        Toast.makeText(this,"Person delete", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}


