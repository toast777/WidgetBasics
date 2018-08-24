package com.chuck.android.widgetbasics.model;

import java.util.ArrayList;
import java.util.List;

public class MovieShowing {

    private String movieTheater;
    private String movieTitle;
    private String movieTimes;

    private MovieShowing (String movieTheater,String movieTitle, String movieTimes)
    {
        this.movieTheater = movieTheater;
        this.movieTitle = movieTitle;
        this.movieTimes = movieTimes;
    }

    public static List<MovieShowing> generateMovieTimes(String currentMovieTheater, Boolean isOdd) {
        List<MovieShowing> currentMovieShowings = new ArrayList<>();
        String oddMovies = "1 3 5 7 9 11";
        String evenMovies = "12 2 4 6 8";
        String movieNames[] = {"Thor","Ant-Man","Incredible Hulk","Silver Surfer", "Avengers"};
        String currentMovieTimes;
        if (isOdd)
            currentMovieTimes = oddMovies;
        else
            currentMovieTimes = evenMovies;
        for (int i = 0; i < 5; i++) {
            currentMovieShowings.add(new MovieShowing(currentMovieTheater,movieNames[i],currentMovieTimes));
        }
        return currentMovieShowings;
    }

    public String getMovieTheater() {
        return movieTheater;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieTimes() {
        return movieTimes;
    }
}
