package com.example.roman.testproject.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.example.roman.testproject.R
import com.example.roman.testproject.ui.favourite.FavouritePersonActivity
import com.example.roman.testproject.ui.search.SearchPersonActivity

open abstract class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private var mSnackBar: Snackbar? = null
    protected lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        registerReceiver(ConnectivityReceiver(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        navigationView = findViewById(R.id.navigation) as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now."
            Toast.makeText(this,"You are offline now", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView.postDelayed({
            val itemId = item.itemId
            if (itemId == R.id.navigation_search) {
                startActivity(Intent(this, SearchPersonActivity::class.java))
            } else if (itemId == R.id.navigation_favourite) {
                startActivity(Intent(this, FavouritePersonActivity::class.java))
            }

            finish()
        }, 300)
        return true
    }

    private fun updateNavigationBarState() {
        val actionId = getNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)
    }

    internal fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }

    internal abstract fun getContentViewId(): Int

    internal abstract fun getNavigationMenuItemId(): Int
}