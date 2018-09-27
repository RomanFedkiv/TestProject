package com.example.roman.testproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.roman.testproject.R
import com.example.roman.testproject.data.model.PersonInfo
import com.example.roman.testproject.presentation.favourite_person.FavouritePersonContract
import com.example.roman.testproject.presentation.search_person.SearchPersonContract
import com.example.roman.testproject.ui.adapter.FavouritePersonAdapter
import com.example.roman.testproject.ui.adapter.PersonAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_favourite_person.*
import kotlinx.android.synthetic.main.dialog_comment.*
import javax.inject.Inject

class FavouritePersonActivity : BaseActivity(), FavouritePersonContract.View {

    @Inject
    override lateinit var presenter: FavouritePersonContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        initView()
        presenter.start()
    }

    fun initView(){
        favourite_list.layoutManager = LinearLayoutManager(this)
        favourite_list.adapter = FavouritePersonAdapter{ personInfo: PersonInfo, s: String ->
            when (s)
            {
                Config.FAVOURITE_BUTTON_PRESSED -> presenter.deleteFromFavourite(personInfo.id!!)
                Config.PDF_BUTTON_PRESSED -> {
                    val intent = Intent(this, PdfReaderActivity::class.java)
                    intent.putExtra("key",personInfo.linkPDF)
                    startActivity(intent)
                }
                Config.COMMENT -> presenter.changeComment(personInfo)
            }
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_favourite_person
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_favourite
    }

    override fun showFavouritePerson(list: List<PersonInfo>) {
        if (list.isEmpty()) empty_text_view.visibility = View.VISIBLE
                       else empty_text_view.visibility = View.INVISIBLE
        (favourite_list.adapter as FavouritePersonAdapter).updateList(list)
    }

    override fun sucessDelete() {
        Toast.makeText(this,"Person delete", Toast.LENGTH_SHORT).show()
    }

    override fun sucessUpdate() {
        Toast.makeText(this,"Comment changed", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}