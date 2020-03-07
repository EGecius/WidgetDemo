package uk.com.egecius.widgetdemo

import android.content.Context
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
