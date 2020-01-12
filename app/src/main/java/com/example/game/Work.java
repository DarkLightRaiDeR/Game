package com.example.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Work implements View.OnClickListener, View.OnTouchListener {

    private Button btnStart, btnRestart;
    private ImageView imgCat, imgMouse;
    private int dX, dY;

    @SuppressLint("ClickableViewAccessibility")
    Work(Activity activity) {
        btnStart = activity.findViewById(R.id.StartButton);
        btnRestart = activity.findViewById(R.id.RestartButton);
        imgCat = activity.findViewById(R.id.Cat);
        imgMouse = activity.findViewById(R.id.Mouse);
        btnStart.setOnClickListener(this);
        btnRestart.setOnClickListener(this);
        imgMouse.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.StartButton:
                btnStart.setVisibility(View.GONE);
                imgCat.setVisibility(View.VISIBLE);
                imgMouse.setVisibility(View.VISIBLE);
                Launch();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                LinearLayout.LayoutParams paramsMouse1 = (LinearLayout.LayoutParams) v.getLayoutParams();
                dX = x - paramsMouse1.leftMargin;
                dY = y - paramsMouse1.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                LinearLayout.LayoutParams paramsMouse2 = (LinearLayout.LayoutParams) v.getLayoutParams();
                paramsMouse2.leftMargin = x - dX;
                paramsMouse2.topMargin = y - dY;
                paramsMouse2.rightMargin = 0;
                paramsMouse2.bottomMargin = 0;
                v.setLayoutParams(paramsMouse2);
        }
        return true;
    }

    void Launch() {
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Mouse mousePerson = new Mouse();
                    Cat catPerson = new Cat();
                    LinearLayout.LayoutParams paramsCat = (LinearLayout.LayoutParams) imgCat.getLayoutParams();
                    if (catPerson.isTakeMouse() == false) {
                        if (dX >= paramsCat.leftMargin & dY >= paramsCat.topMargin) {
                            paramsCat.leftMargin -= catPerson.getSpeed();
                            paramsCat.topMargin -= catPerson.getSpeed();
                            paramsCat.rightMargin = 0;
                            paramsCat.bottomMargin = 0;
                            imgCat.setLayoutParams(paramsCat);
                        }
                        if (dX >= paramsCat.leftMargin & dY <= paramsCat.topMargin) {
                            paramsCat.leftMargin -= catPerson.getSpeed();
                            paramsCat.topMargin += catPerson.getSpeed();
                            paramsCat.rightMargin = 0;
                            paramsCat.bottomMargin = 0;
                            imgCat.setLayoutParams(paramsCat);
                        }
                        if (dX <= paramsCat.leftMargin & dY >= paramsCat.topMargin) {
                            paramsCat.leftMargin += catPerson.getSpeed();
                            paramsCat.topMargin -= catPerson.getSpeed();
                            paramsCat.rightMargin = 0;
                            paramsCat.bottomMargin = 0;
                            imgCat.setLayoutParams(paramsCat);
                        }
                        if (dX <= paramsCat.leftMargin & dY <= paramsCat.topMargin) {
                            paramsCat.leftMargin += catPerson.getSpeed();
                            paramsCat.topMargin += catPerson.getSpeed();
                            paramsCat.rightMargin = 0;
                            paramsCat.bottomMargin = 0;
                            imgCat.setLayoutParams(paramsCat);
                        }
                    }
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }
