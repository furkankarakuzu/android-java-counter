package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    Button increaseBtn;
    Button decreaseBtn;
    Button settingsBtn;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = SharedPref.getInstance(this);        resultText = (TextView)findViewById(R.id.resultText);
        increaseBtn = (Button)findViewById(R.id.increaseBtn);
        decreaseBtn = (Button)findViewById(R.id.decreaseBtn);
        settingsBtn = (Button)findViewById(R.id.buttonSettings);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(this, R.raw.beep);
        settingsBtn.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
        increaseBtn.setOnClickListener(view->{
            setValue(1);
        });

        decreaseBtn.setOnClickListener(view->{
            setValue(-1);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        resultText.setText(String.valueOf(sharedPref.value));
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPref.saveValues();
    }

    private void setValue(int count){
        if(count>0){
            if(sharedPref.value+count> sharedPref.upperLimit){
                if(sharedPref.upperLimitSound){
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                }
                if(sharedPref.upperLimitVib){
                    Vibrate();
                }
                sharedPref.value= sharedPref.upperLimit;
            }else{
                sharedPref.value+=count;
            }
        }else{
            if(sharedPref.value+count<sharedPref.lowerLimit){
                if(sharedPref.lowerLimitSound){
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                }
                if(sharedPref.lowerLimitVib){
                    Vibrate();
                }
                sharedPref.value= sharedPref.lowerLimit;
            }else{
                sharedPref.value+=count;
            }
        }
        resultText.setText(String.valueOf(sharedPref.value));
    }

    private void Vibrate(){
        if(!vibrator.hasVibrator())
            return;

        vibrator.vibrate(1000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            setValue(5);
        }else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            setValue(-5);
        }
        return super.onKeyDown(keyCode, event);
    }
}