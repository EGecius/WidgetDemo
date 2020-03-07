package uk.com.egecius.widgetdemo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log

/**
 * Defines the basic methods that allow you to programmatically interface with the App Widget, based on broadcast events.
 * Through it, you will receive broadcasts when the App Widget is updated, enabled, disabled and deleted
 * */
class ExampleAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.v("Eg:ExampleAppWidgetProvider:19", "onUpdate() ")
    }
}