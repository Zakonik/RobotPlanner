package com.example.robotplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonUp = findViewById(R.id.upButton);
        final Button buttonDown = findViewById(R.id.downButton);
        final Button buttonRight = findViewById(R.id.rightButton);
        final Button buttonLeft = findViewById(R.id.leftButton);
        final Button buttonCalendar = findViewById(R.id.calendarButton);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               buttonUp.setText("LOL");
            }
        });
        buttonDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,calendar.class));
            }
        });
    }
}
