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

    Thread pressingUp = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                while (pressedUp.get() && !pressedDown.get() && !pressedLeft.get() && !pressedRight.get()) {
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage("u");
                    }
                    SystemClock.sleep(100);
                }
            }
        }
    });

    AtomicBoolean pressedDown = new AtomicBoolean(false);

    Thread pressingDown = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                while (pressedDown.get() && !pressedUp.get() && !pressedLeft.get() && !pressedRight.get()) {
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage("d");
                    }
                    SystemClock.sleep(100);
                }
            }
        }
    });

    AtomicBoolean pressedLeft = new AtomicBoolean(false);

    Thread pressingLeft = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                while (pressedLeft.get() && !pressedRight.get() && !pressedUp.get() && !pressedDown.get()) {
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage("l");
                    }
                    SystemClock.sleep(100);
                }
            }
        }
    });

    AtomicBoolean pressedRight = new AtomicBoolean(false);

    Thread pressingRight = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                while (pressedRight.get() && !pressedLeft.get() && !pressedUp.get() && !pressedDown.get()) {
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage("r");
                    }
                    SystemClock.sleep(100);
                }
            }
        }
    });


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

