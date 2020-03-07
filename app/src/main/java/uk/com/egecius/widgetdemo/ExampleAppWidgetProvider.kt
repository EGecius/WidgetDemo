package uk.com.egecius.widgetdemo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.util.Log

/**
 * Defines the basic methods that allow you to programmatically interface with the App Widget, based on broadcast events.
 * Through it, you will receive broadcasts when the App Widget is updated, enabled, disabled and deleted
 * */
class ExampleAppWidgetProvider : AppWidgetProvider() {

    /**
     * called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo
     * */
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.v("Eg:ExampleAppWidgetProvider:19", "onUpdate() ")
    }

    /**
     *  Called when the widget is first placed and any time the widget is resized. You can use this
     *  callback to show or hide content based on the widget's size ranges. You get the size ranges
     *  by calling getAppWidgetOptions(), which returns a Bundle */
    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        Log.v("Eg:ExampleAppWidgetProvider:29", "onAppWidgetOptionsChanged()")
    }
}