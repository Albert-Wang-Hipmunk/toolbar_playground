package com.example.mooqoo.customactionbar

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

// TODO create a base activity
class MainActivity : AppCompatActivity() {

    val TAG = "Toolbar Playground"

    enum class ToolbarType {
        DEFAULT,
        TYPE1,
        TYPE2
    }

    enum class MenuType {
        DEFAULT,
        TYPE1,
        TYPE2
    }

    var menuType = MenuType.DEFAULT
    var toolbarType = ToolbarType.DEFAULT

    private val customToolbarContent: View by lazy {
        val view = layoutInflater.inflate(R.layout.toolbar_title, null)
        view.findViewById<TextView>(R.id.tb_title).setOnClickListener {
            Toast.makeText(this, "title clicked", Toast.LENGTH_SHORT).show()
        }
        view
    }
    private val customToolbarContent2: View by lazy {
        val view = layoutInflater.inflate(R.layout.toolbar_custom, null)
        view.run {
            findViewById<TextView>(R.id.textView).setOnClickListener {
                Toast.makeText(this@MainActivity, "left text clicked", Toast.LENGTH_SHORT).show()
            }
            findViewById<TextView>(R.id.textView2).setOnClickListener {
                Toast.makeText(this@MainActivity, "center text clicked", Toast.LENGTH_SHORT).show()
            }
            findViewById<TextView>(R.id.textView3).setOnClickListener {
                Toast.makeText(this@MainActivity, "right text clicked", Toast.LENGTH_SHORT).show()
            }
        }
        view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_container)

        setupDefaultToolbar()

        fab.setOnClickListener {
            Log.d(TAG, "Fab is clicked")
            changeToolbarMenu()
            changeCustomToolbar()
        }
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

    private fun setupDefaultToolbar() {
        toolbar_container.title = "this is title"
        toolbar_container.subtitle = "this is subtitle"
        invalidateOptionsMenu()
    }

    private fun changeCustomToolbar() {
        toolbar_container.removeAllViews()
        when(toolbarType) {
            ToolbarType.DEFAULT -> {
                toolbarType = ToolbarType.TYPE1
                toolbar_container.addView(customToolbarContent)
            }
            ToolbarType.TYPE1 -> {
                toolbarType = ToolbarType.TYPE2
                supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                val params = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
                )
                supportActionBar?.setCustomView(customToolbarContent2, params)
            }
            ToolbarType.TYPE2 -> {
                toolbarType = ToolbarType.DEFAULT
                setupDefaultToolbar()
            }
        }
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
