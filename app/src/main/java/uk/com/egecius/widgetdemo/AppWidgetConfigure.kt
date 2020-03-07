package uk.com.egecius.widgetdemo

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_app_widget_congifure.*

/**
 * Automatically launched by the App Widget host and allows the user to configure available settings for
 * the App Widget at create-time, such as the
 * App Widget color, size, update period or other functionality settings.
 *
 * configuration Activity should always return a result. The result should include the App Widget
 * ID passed by the Intent that launched the Activity (saved in the Intent extras as EXTRA_APPWIDGET_ID
 *
 * */
class AppWidgetConfigure : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_widget_congifure)

        setClickListener()
    }

    private fun setClickListener() {
        button_confirmation.setOnClickListener {
            val text = edit_text_configuration.text.toString()
            finishConfiguration(text)
        }
    }

    private fun finishConfiguration(configurationText: String) {
        val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)

        RemoteViews(packageName, R.layout.example_appwidget).also { views ->
            appWidgetManager.updateAppWidget(getWidgetId(), views)
        }

        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, getWidgetId())
            putExtra(KEY_CONFIGURATION_TEXT, configurationText)
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