package com.chuck.android.widgetbasics.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.chuck.android.widgetbasics.R;
import com.chuck.android.widgetbasics.model.MovieShowing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieShowingWidgetAdaptor implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private List<MovieShowing> movieShowings;


    public MovieShowingWidgetAdaptor(Context context) {
        this.context = context;
        this.movieShowings = getMovieShowings();
    }
    private List<MovieShowing> getMovieShowings() {
        movieShowings = null;
        SharedPreferences sharedPreferences;
        if ((sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null)
        {
            String listJson = sharedPreferences.getString("json1", "No Data");
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<MovieShowing>>() {
            }.getType();
            movieShowings = gson.fromJson(listJson, type);
        }
        return movieShowings;
    }
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_showing_widget_list_item);
            String holder;
            if ((holder = movieShowings.get(position).getMovieTitle()) != null)
                remoteViews.setTextViewText(R.id.appwidget_movie_name, holder);
            if ((holder = movieShowings.get(position).getMovieTimes()) != null)
                remoteViews.setTextViewText(R.id.appwidget_movie_times, holder);
            return remoteViews;
    }
    @Override
    public int getCount() {
        return movieShowings.size();
    }
    @Override
    public void onDataSetChanged() {
        movieShowings = getMovieShowings();
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    //Kept Defaults on these methods

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public void onCreate() {

    }
    @Override
    public void onDestroy() {

    }
}
