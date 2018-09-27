package com.example.roman.testproject.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.ui.adapter.PersonAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject
import android.widget.EditText
import es.voghdev.pdfviewpager.library.asset.CopyAssetThreadImpl
import es.voghdev.pdfviewpager.library.asset.CopyAsset
import java.io.File
import es.voghdev.pdfviewpager.library.PDFViewPager
import kotlinx.android.synthetic.main.dialog_comment.*
import kotlinx.android.synthetic.main.person_info_item.*


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
            if (s.equals(Config.FAVOURITE_BUTTON_PRESSED)) {
                showCreateCategoryDialog(personInfo)
            }
            if (s.equals(Config.FAVOURITE_BUTTON_DEFAULT)){
                presenter.deleteFromFavourite(personInfo.id!!)
            }
            if (s.equals(Config.PDF_BUTTON_PRESSED)) {
                val intent = Intent(this, PdfReaderActivity::class.java)
                intent.putExtra("key",personInfo.linkPDF)
                startActivity(intent)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


