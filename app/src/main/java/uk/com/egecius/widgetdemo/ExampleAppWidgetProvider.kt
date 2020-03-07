package uk.com.egecius.widgetdemo

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews

/**
 * Defines the basic methods that allow you to programmatically interface with the App Widget, based on broadcast events.
 * Through it, you will receive broadcasts when the App Widget is updated, enabled, disabled and deleted
 * */
class ExampleAppWidgetProvider : AppWidgetProvider() {

    /**
     * Called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo
     * */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Log.v("Eg:ExampleAppWidgetProvider:19", "onUpdate() ")

        setOnClickListeners(appWidgetIds, context, appWidgetManager)
        readFromSharePreferences(context)
    }

    private fun readFromSharePreferences(context: Context) {
        val string = context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE)
            .getString(KEY_WIDGET_TEXT, null)

        Log.v("Eg:ExampleAppWidgetProvider:32", "readFromSharePreferences() string: $string")
    }

    private fun setOnClickListeners(
        appWidgetIds: IntArray,
        context: Context,
        appWidgetManager: AppWidgetManager
    ) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds.forEach { appWidgetId ->
            val remoteViews: RemoteViews = createLayoutWithListenersSet(context)
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    private fun createLayoutWithListenersSet(context: Context): RemoteViews {
        return RemoteViews(
            context.packageName, R.layout.example_appwidget
        ).apply {
            val pendingIntent: PendingIntent = createIntentForExampleActivity(context)
            setOnClickPendingIntent(R.id.example_button, pendingIntent)
        }
    }

    private fun createIntentForExampleActivity(context: Context): PendingIntent {
        return Intent(context, ExampleActivity::class.java)
            .let {
                PendingIntent.getActivity(context, 0, it, 0)
            }
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