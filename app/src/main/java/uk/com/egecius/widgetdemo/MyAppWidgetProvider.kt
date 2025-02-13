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
class MyAppWidgetProvider : AppWidgetProvider() {

    /**
     * Called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo
     * */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Log.i("Eg:MyAppWidgetProvider:22", "onUpdate() ")
        updateAllRemoteViews(appWidgetIds, context)
    }

    private fun updateAllRemoteViews(
        appWidgetIds: IntArray,
        context: Context
    ) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds.forEach { appWidgetId ->
            val text = readTextFromSharePreferences(context)
            updateWidget(context, appWidgetId, text)
        }
    }

    private fun readTextFromSharePreferences(context: Context): String? {
        return context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE)
            .getString(KEY_WIDGET_TEXT, null)
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
        Log.v("Eg:MyAppWidgetProvider:29", "onAppWidgetOptionsChanged()")
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.v("Eg:MyAppWidgetProvider:78", "onEnabled() - widget created to add to the home screen, while there are none added yet")
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.v("Eg:MyAppWidgetProvider:83", "onDisabled() - last widget removed from the home screen")
    }


    companion object {

        fun updateWidget(context: Context, appWidgetId: Int, text: String?) {
            val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews: RemoteViews = createLayoutWithListenersSet(context, text)
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }

        private fun createLayoutWithListenersSet(context: Context, text: String?): RemoteViews {
            return RemoteViews(
                context.packageName, R.layout.example_appwidget
            ).apply {
                Log.d("Eg:MyAppWidgetProvider:45", "createLayoutWithListenersSet() text: $text")
                setTextViewText(R.id.text_content, text)
                val pendingIntent: PendingIntent = createIntentForExampleActivity(context)
                setOnClickPendingIntent(R.id.example_button, pendingIntent)
            }
        }

        private fun createIntentForExampleActivity(context: Context): PendingIntent {
            return Intent(context, LaunchedFromWidgetActivity::class.java)
                .let {
                    PendingIntent.getActivity(context, 0, it, 0)
                }
        }
    }
}