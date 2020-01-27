package com.example.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Work extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private Button btnStart, btnRestart;
    private ImageView imgCat, imgMouse;
    private TextView textView;
    private float dX, dY;
    private Activity mActivity;

    @SuppressLint("ClickableViewAccessibility")
    Work(Activity activity) {
        btnStart = activity.findViewById(R.id.StartButton);
        btnRestart = activity.findViewById(R.id.RestartButton);
        imgCat = activity.findViewById(R.id.Cat);
        imgMouse = activity.findViewById(R.id.Mouse);
        textView = activity.findViewById(R.id.textView);
        btnStart.setOnClickListener(this);
        btnRestart.setOnClickListener(this);
        imgMouse.setOnTouchListener(this);
        mActivity = activity;
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
            case R.id.RestartButton:
                restartActivity(mActivity);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final float x = event.getRawX();
        final float y = event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                dX = x - v.getX();
                dY = y - v.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                v.setX(x - dX);
                v.setY(y - dY);
        }
        return true;
    }

    void Launch() {
        final Cat catPerson = new Cat();
            final Timer timer = new Timer();
            final TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {

                    if (!catPerson.isTakeMouse()) {

                        if (imgMouse.getX() > imgCat.getX() & imgMouse.getY() > imgCat.getY()) {
                            imgCat.setX(imgCat.getX() + catPerson.getSpeed());
                            imgCat.setY(imgCat.getY() + catPerson.getSpeed());
                        }
                        if (imgMouse.getX() > imgCat.getX() & imgMouse.getY() < imgCat.getY()) {
                            imgCat.setX(imgCat.getX() + catPerson.getSpeed());
                            imgCat.setY(imgCat.getY() - catPerson.getSpeed());
                        }
                        if (imgMouse.getX() < imgCat.getX() & imgMouse.getY() > imgCat.getY()) {
                            imgCat.setX(imgCat.getX() - catPerson.getSpeed());
                            imgCat.setY(imgCat.getY() + catPerson.getSpeed());
                        }
                        if (imgMouse.getX() < imgCat.getX() & imgMouse.getY() < imgCat.getY()) {
                            imgCat.setX(imgCat.getX() - catPerson.getSpeed());
                            imgCat.setY(imgCat.getY() - catPerson.getSpeed());
                        }

                        if (((imgMouse.getX() < (imgCat.getX() + imgCat.getWidth())) &&
                                (imgCat.getX() < (imgMouse.getX() + imgMouse.getWidth())) &&
                                (imgMouse.getY() < (imgCat.getY() + imgCat.getHeight())) &&
                                (imgCat.getY() < (imgMouse.getY() + imgMouse.getHeight())))) {
                            catPerson.setTakeMouse(true);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imgCat.setVisibility(View.GONE);
                                    imgMouse.setVisibility(View.GONE);
                                    textView.setVisibility(View.VISIBLE);
                                    btnRestart.setVisibility(View.VISIBLE);
                                }
                            });
                            timer.cancel();
                        }
                    }
                }
            };
            timer.schedule(timerTask, 0, 16);
        }

    public static void restartActivity(Activity activity) {
        activity.recreate();
    }
    }
