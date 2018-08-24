package com.chuck.android.widgetbasics;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.chuck.android.widgetbasics.model.MovieShowing;

import java.util.List;
import java.util.Random;

public class MovieTimesActivity extends AppCompatActivity {

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
        //Problem no append method for remoteviews so appending one string to send to it
        String widgetMovieTimes = "";
        List<MovieShowing> currentMovieTheaterTimes = MovieShowing.generateMovieTimes(currentTheaterName, r.nextBoolean());
        for (MovieShowing currentMovie : currentMovieTheaterTimes) {
            movieTimes.append(currentMovie.getMovieTitle() + "\n");
            widgetMovieTimes = widgetMovieTimes + currentMovie.getMovieTitle() + "\n";
            movieTimes.append(currentMovie.getMovieTimes() + "\n\n");
            widgetMovieTimes = widgetMovieTimes + currentMovie.getMovieTimes() + "\n\n";
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.new_app_widget);
        remoteViews.setTextViewText(R.id.appwidget_movie_theater, currentTheaterName);
        remoteViews.setTextViewText(R.id.appwidget_movie_times, widgetMovieTimes);
        ComponentName thisWidget = new ComponentName(this, NewAppWidget.class);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);

    }
    //Kept this in to show how not to do it, call it multiple times erases the existing view
    public void updateMovieWidget(String widgetText, int textBoxID){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.new_app_widget);
        remoteViews.setTextViewText(textBoxID, widgetText);
        ComponentName thisWidget = new ComponentName(this, NewAppWidget.class);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }
}
