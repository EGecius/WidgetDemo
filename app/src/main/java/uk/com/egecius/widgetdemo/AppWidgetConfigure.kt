package uk.com.egecius.widgetdemo

import android.appwidget.AppWidgetManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

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

        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        Log.v("Eg:AppWidgetConfigure:27", "onCreate() appWidgetId: $appWidgetId")

    }
}