package com.example.roman.testproject.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.ui.adapter.PersonAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject
import android.widget.EditText


class SearchPersonActivity : SearchPersonContract.View, BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_search
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_search
    }

    @Inject
    override lateinit var presenter: SearchPersonContract.Presenter

    val DIALOG_EXIT = 1
    lateinit var commentText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)
        AndroidInjection.inject(this)
        initView()
    }

    private fun initView() {
        search_button.setOnClickListener {
            presenter.initSearch(query_edit.text.toString())
        }

        result_list.layoutManager = LinearLayoutManager(this)
        result_list.adapter = PersonAdapter{
            showCreateCategoryDialog()
            presenter.addToFavourite(it)
        }
    }

    fun showCreateCategoryDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        builder.setTitle("New Category")

        val view = layoutInflater.inflate(R.layout.dialog_comment, null)

        val categoryEditText = view.findViewById(R.id.comment) as EditText

        builder.setView(view);

        builder.setPositiveButton(android.R.string.ok)  { dialog, p1 ->

            var isValid = true

            if (isValid) {
                commentText = categoryEditText.text.toString()
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show();
    }


    override fun showPersonInfo(listPerson: List<PersonInfo>) {
        (result_list.adapter as PersonAdapter).updateList(listPerson)
    }

    override fun showError() {

    }

    override fun successAdd() {
        Toast.makeText(this,"Person add to favourite",Toast.LENGTH_SHORT).show()

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


