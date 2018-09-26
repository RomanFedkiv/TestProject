package com.example.roman.testproject.ui

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
import javax.inject.Inject

class FavouritePersonActivity : BaseActivity(), FavouritePersonContract.View {

    override fun getContentViewId(): Int {
        return R.layout.activity_favourite_person
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_favourite
    }

    @Inject
    override lateinit var presenter: FavouritePersonContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_favourite_person)
        AndroidInjection.inject(this)
        initView()
        presenter.start()
    }

    fun initView(){
        favourite_list.layoutManager = LinearLayoutManager(this)
        favourite_list.adapter = FavouritePersonAdapter{
            presenter.deleteFromFavourite(it.id!!)
        }
    }

    override fun showFavouritePerson(list: List<PersonInfo>) {
        if (list.isEmpty()) empty_text_view.visibility = View.VISIBLE
        else {
            empty_text_view.visibility = View.INVISIBLE
            (favourite_list.adapter as FavouritePersonAdapter).updateList(list)
        }
    }

    override fun sucessDelete() {

        Toast.makeText(this,"Person delete", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}