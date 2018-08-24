package com.chuck.android.widgetbasics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioMovieTheaterGroup;
    private RadioButton radioMovieTheaterButton;
    public static final String EXTRA_MOVIETHEATERNAME = "Movie Theater Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioMovieTheaterGroup = findViewById(R.id.movieTheaterGroup);
        Button btnSubmit = findViewById(R.id.submitButton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedMovieTheater = radioMovieTheaterGroup.getCheckedRadioButtonId();
                //Send intent to Second Activity
                radioMovieTheaterButton = findViewById(selectedMovieTheater);
                Intent intent = new Intent(getApplicationContext(), MovieTimesActivity.class);
                intent.putExtra(EXTRA_MOVIETHEATERNAME, radioMovieTheaterButton.getText());
                startActivity(intent);
            }
        });
    }
}
