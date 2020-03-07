package uk.com.egecius.widgetdemo

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_widget_congifuration.*

/**
 * Automatically launched by the App Widget host and allows the user to configure available settings for
 * the App Widget at create-time, such as the
 * App Widget color, size, update period or other functionality settings.
 *
 * configuration Activity should always return a result. The result should include the App Widget
 * ID passed by the Intent that launched the Activity (saved in the Intent extras as EXTRA_APPWIDGET_ID
 *
 * */
class WidgetConfigurationActivity : AppCompatActivity() {

    private val sharePrefsEditor by lazy {
        getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE).edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_congifuration)

        setClickListener()
    }

    private fun setClickListener() {
        button_confirmation.setOnClickListener {
            val text = edit_text_configuration.text.toString()
            saveConfigurationResult(text)
            finishConfiguration(text)
        }
    }

    private fun saveConfigurationResult(text: String) {

        Log.v("Eg:WidgetConfigurationActivity:44", "saveConfigurationResult() text: $text")

        sharePrefsEditor
            .putString(KEY_WIDGET_TEXT, text)
            .apply()
    }

    private fun finishConfiguration(text: String) {
        MyAppWidgetProvider.updateAppWidget(this, getWidgetId(), text)

        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, getWidgetId())
        }
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

    private fun getWidgetId(): Int {
        return intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }
}