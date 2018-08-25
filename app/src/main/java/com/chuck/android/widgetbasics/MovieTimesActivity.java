package com.chuck.android.widgetbasics;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.chuck.android.widgetbasics.model.MovieShowing;
import com.chuck.android.widgetbasics.widget.MovieShowingWidget;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

public class MovieTimesActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_times);
        Intent intent = getIntent();
        String currentTheaterName = intent.getStringExtra(MainActivity.EXTRA_MOVIETHEATERNAME);
        TextView movieTheaterName = findViewById(R.id.movie_theater_name);
        movieTheaterName.setText(currentTheaterName);
        TextView movieTimes = findViewById(R.id.movie_times);
        //Randomize movie showtimes by using random odd/even times
        Random r = new Random();
        List<MovieShowing> currentMovieTheaterTimes = MovieShowing.generateMovieTimes(currentTheaterName, r.nextBoolean());
        for (MovieShowing currentMovie : currentMovieTheaterTimes) {
            movieTimes.append(currentMovie.getMovieTitle() + "\n");
            movieTimes.append(currentMovie.getMovieTimes() + "\n\n");
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        Gson gsonIngList = new Gson();
        String json = gsonIngList.toJson(currentMovieTheaterTimes);
        editor.putString("json1", json);
        editor.putString("Movie Theater Name", currentTheaterName);
        editor.apply();

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.movie_showing_widget);
        remoteViews.setTextViewText(R.id.appwidget_movie_theater, currentTheaterName + " showings");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName thisWidget = new ComponentName(this, MovieShowingWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_movie_screenings);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }

}
