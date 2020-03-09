package uk.com.egecius.widgetdemo

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val sharePrefsEditor by lazy {
        getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE).edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEditTextListener()
        setClickListener()
    }

    private fun setClickListener() {
        add_widget.setOnClickListener {
            val pickIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_PICK)
            // not sure if it's possible to get id of my widget
            val appWidgetID = "91397"
            pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetID)
            startActivityForResult(pickIntent, SHOW_WIDGETS_REQUEST_CODE)
        }
    }

    private fun setEditTextListener() {
        widget_edit_text.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(editable: Editable?) {
                writeToSharedPrefs(editable.toString())
            }
        })
    }

    private fun writeToSharedPrefs(text: String) {
        Log.v("Eg:MainActivity:30", "writeToSharedPrefs() text: $text")
        sharePrefsEditor
            .putString(KEY_WIDGET_TEXT, text)
            .apply()
    }
}
