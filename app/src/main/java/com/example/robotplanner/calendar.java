package com.example.robotplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calendar extends AppCompatActivity {

    Button buttonAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        buttonAddItem = (Button)findViewById(R.id.btn_add_item);
        buttonAddItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                if(v==buttonAddItem){

                    Intent intent = new Intent(getApplicationContext(),AddItem.class);
                    startActivity(intent);
                }

            }
        });

        final Button buttonController = findViewById(R.id.controllerButton);

        buttonController.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


}
