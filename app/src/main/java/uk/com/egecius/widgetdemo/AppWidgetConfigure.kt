package uk.com.egecius.widgetdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Automatically launched by the App Widget host and allows the user to configure available settings for
 * the App Widget at create-time, such as the
 * App Widget color, size, update period or other functionality settings. */
class AppWidgetConfigure : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_widget_congifure)
    }
}