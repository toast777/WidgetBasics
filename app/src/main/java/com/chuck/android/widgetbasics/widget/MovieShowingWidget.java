package com.chuck.android.widgetbasics.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.chuck.android.widgetbasics.R;

/**
 * Implementation of App Widget functionality.
 */
public class MovieShowingWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        // Fix for not updating last widget
        int[] realAppWidgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, MovieShowingWidget.class));
        for (int id : realAppWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_showing_widget);
            Intent serviceIntent = new Intent(context, MovieShowingWidgetService.class);
            remoteViews.setRemoteAdapter(R.id.widget_list_movie_screenings, serviceIntent);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String movieTheaterTitle = sharedPreferences.getString("Movie Theater Name", "No Movie Theater Selected");
            remoteViews.setTextViewText(R.id.appwidget_movie_theater, movieTheaterTitle + " showings");

            Intent intent = new Intent(context, MovieShowingWidget.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            remoteViews.setPendingIntentTemplate(R.id.widget_list_movie_screenings, pendingIntent);
            appWidgetManager.updateAppWidget(id, remoteViews);        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

