package uk.com.egecius.widgetdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LaunchedFromWidgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lauched_from_widget)
    }
}
