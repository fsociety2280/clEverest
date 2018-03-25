package ru.myitschool.cleverempiers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

public class Intro extends AppCompatActivity {
    Button buttonNewGame, buttonHowToPlay, buttonExit;
    public static final int SOUND_BUTTON = 0;
    private SoundPool soundPool;
    private Map<Integer, Integer> soundMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        fullHD();
        sizeScr();
        V.calculateCoefficientScreen();
        createButtons();
        setClickers();
        initilizeSound(this);
    }

    private void fullHD() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    private void sizeScr() {
        V.scrWidth = getResources().getDisplayMetrics().widthPixels;
        V.scrHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private void createButtons() {
        float x, y;
        int widthButton, heightButton;
        widthButton = (V.scrWidth > V.scrHeight ? V.scrWidth : V.scrHeight) / 3;
        heightButton = (int) (widthButton / V.KOEFF_BUTTON_INTRO);
        x = V.scrWidth / 2 - widthButton / 2;
        y = V.scrHeight / 2 - heightButton / 2;
        buttonNewGame = new Button(this, x, y, widthButton, heightButton, "New Game");
        y = V.scrHeight / 2 - heightButton * 2;
        buttonHowToPlay = new Button(this, x, y, widthButton, heightButton, "How To Play");
        y = V.scrHeight / 2 + heightButton;
        buttonExit = new Button(this, x, y, widthButton, heightButton, "Exit");
    }

    private void setClickers() {
        buttonNewGame.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonNewGame.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonNewGame.buttonUp();
                    startActivity(new Intent(Intro.this, MainActivity.class));
                }
                return true;
            }
        });

        buttonHowToPlay.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonHowToPlay.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonHowToPlay.buttonUp();
                    startActivity(new Intent(Intro.this, HTP.class));
                }
                return true;
            }
        });

        buttonExit.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonExit.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonExit.buttonUp();
                    System.exit(0);
                }
                return true;
            }
        });
    }

    private void initilizeSound(Context context) {
        int MAX_STREAMS = 4;
        int SOUND_QUALITY = 100;
        int priority = 1;
        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, SOUND_QUALITY);
        soundMap = new HashMap<>();
        soundMap.put(SOUND_BUTTON, soundPool.load(context, R.raw.sound_button, priority));

    }
}
