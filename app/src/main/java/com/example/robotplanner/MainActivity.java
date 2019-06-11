package com.example.robotplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    TcpClient mTcpClient;

    AtomicBoolean pressedUp = new AtomicBoolean(false);
    AtomicBoolean pressedDown = new AtomicBoolean(false);
    AtomicBoolean pressedLeft = new AtomicBoolean(false);
    AtomicBoolean pressedRight = new AtomicBoolean(false);




    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //response received from server
            Log.d("test", "response " + values[0]);
            //process server response here....

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonUp = findViewById(R.id.upButton);
        final Button buttonDown = findViewById(R.id.downButton);
        final Button buttonRight = findViewById(R.id.rightButton);
        final Button buttonLeft = findViewById(R.id.leftButton);
        final Button buttonCalendar = findViewById(R.id.calendarButton);
        final Button buttonConnect = findViewById(R.id.connectButton);
        final Button buttonShowList = findViewById(R.id.showListButton);
        pressingUp.start();
        pressingDown.start();
        pressingRight.start();
        pressingLeft.start();

        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressedUp.set(true);
                        if (mTcpClient != null && !pressedLeft.get() && !pressedDown.get() && !pressedRight.get()) {
                            mTcpClient.sendMessage("u");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        pressedUp.set(false);
                        if (mTcpClient != null) {
                            mTcpClient.sendMessage("s");
                        }
                        break;
                }
                return false;
            }
        });
        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressedDown.set(true);
                        if (mTcpClient != null && !pressedLeft.get() && !pressedUp.get() && !pressedRight.get()) {
                            mTcpClient.sendMessage("d");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        pressedDown.set(false);
                        if (mTcpClient != null) {
                            mTcpClient.sendMessage("s");
                        }
                        break;
                }
                return false;
            }
        });
        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressedLeft.set(true);
                        if (mTcpClient != null && !pressedUp.get() && !pressedDown.get() && !pressedRight.get()) {
                            mTcpClient.sendMessage("l");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        pressedLeft.set(false);
                        if (mTcpClient != null) {
                            mTcpClient.sendMessage("s");
                        }
                        break;
                }
                return false;
            }
        });
        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressedRight.set(true);
                        if (mTcpClient != null && !pressedLeft.get() && !pressedDown.get() && !pressedUp.get()) {
                            mTcpClient.sendMessage("r");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        pressedRight.set(false);
                        if (mTcpClient != null) {
                            mTcpClient.sendMessage("s");
                        }
                        break;
                }
                return false;
            }
        });

        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });



        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,calendar.class));
            }
        });

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ConnectTask().execute("");
            }
        });
    }
}

