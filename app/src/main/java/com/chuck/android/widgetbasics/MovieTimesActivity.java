package com.chuck.android.widgetbasics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        List<MovieShowing> currentMovieTheaterTimes = MovieShowing.generateMovieTimes(currentTheaterName, r.nextBoolean());
        for (MovieShowing currentMovie : currentMovieTheaterTimes) {
            movieTimes.append(currentMovie.getMovieTitle() + "\n");
            movieTimes.append(currentMovie.getMovieTimes() + "\n\n");
        }
    }
}
