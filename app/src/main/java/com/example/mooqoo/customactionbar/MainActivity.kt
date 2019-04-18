package com.example.mooqoo.customactionbar

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS

import kotlinx.android.synthetic.main.activity_main.*

// TODO create a base activity
class MainActivity : AppCompatActivity() {
    enum class MenuType {
        DEFAULT,
        TYPE1,
        TYPE2
    }

    var menuType = MenuType.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -> changeToolbarMenu() }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        when (menuType) {
            MenuType.DEFAULT -> { menuInflater.inflate(R.menu.menu_main, menu) }
            MenuType.TYPE1 -> { menu.add(getString(R.string.action_done)).setShowAsAction(SHOW_AS_ACTION_ALWAYS) }
            MenuType.TYPE2 -> {
                menu.add(getString(R.string.action_done)).setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                menu.add(getString(R.string.action_cancel)).setShowAsAction(SHOW_AS_ACTION_ALWAYS)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onMenuItemPress() {

    }

    private fun changeToolbarMenu() {
        when(menuType) {
            MenuType.DEFAULT -> { menuType = MenuType.TYPE1 }
            MenuType.TYPE1 -> { menuType = MenuType.TYPE2 }
            MenuType.TYPE2 -> { menuType = MenuType.DEFAULT }
        }
        invalidateOptionsMenu()
    }
}
