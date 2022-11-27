package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    EditText UpperText;
    Button UpperLimitIncreaseBtn;
    Button UpperLimitDecreaseBtn;
    Switch UpperLimitSoundSwitch;
    Switch UpperLimitVibSwitch;

    EditText LowerText;
    Button LowerLimitIncreaseBtn;
    Button LowerLimitDecreaseBtn;
    Switch LowerLimitSoundSwitch;
    Switch LowerLimitVibSwitch;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        UpperText = (EditText) findViewById(R.id.textUpperLimit);
        UpperLimitIncreaseBtn = (Button)findViewById(R.id.increaseUpperBtn);
        UpperLimitDecreaseBtn = (Button)findViewById(R.id.decreaseUpperBtn);
        UpperLimitSoundSwitch = (Switch) findViewById(R.id.upperSoundSwitch);
        UpperLimitVibSwitch = (Switch) findViewById(R.id.VibUpperSwitch);
        sharedPref = SharedPref.getInstance(this);
        LowerText = (EditText) findViewById(R.id.textLowerLimit);
        LowerLimitIncreaseBtn = (Button)findViewById(R.id.increaseLowerBtn);
        LowerLimitDecreaseBtn = (Button)findViewById(R.id.decreaseLowerBtn);
        LowerLimitSoundSwitch = (Switch) findViewById(R.id.lowerSoundSwitch);
        LowerLimitVibSwitch = (Switch) findViewById(R.id.vibLowerSwitch);

        UpperLimitIncreaseBtn.setOnClickListener(view->{
            sharedPref.upperLimit++;
            UpperText.setText(String.valueOf(sharedPref.upperLimit));
        });
        UpperLimitDecreaseBtn.setOnClickListener(view->{
            sharedPref.upperLimit--;
            UpperText.setText(String.valueOf(sharedPref.upperLimit));
        });
        UpperLimitSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.upperLimitSound = b;
            }
        });
        UpperLimitVibSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.upperLimitVib = b;
            }
        });
        UpperText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(UpperText.getText().toString().length()==0){
                    UpperText.setText("0");
                    sharedPref.upperLimit = 0;
                }else{
                    sharedPref.upperLimit = Integer.valueOf(UpperText.getText().toString());
                }
            }
        });

        LowerLimitIncreaseBtn.setOnClickListener(view->{
            sharedPref.lowerLimit++;
            LowerText.setText(String.valueOf(sharedPref.lowerLimit));
        });
        LowerLimitDecreaseBtn.setOnClickListener(view->{
            sharedPref.lowerLimit--;
            LowerText.setText(String.valueOf(sharedPref.lowerLimit));
        });
        LowerLimitSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.lowerLimitSound = b;
            }
        });
        LowerLimitVibSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.lowerLimitVib = b;
            }
        });
        LowerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(LowerText.getText().toString().length()==0){
                    LowerText.setText("0");
                    sharedPref.lowerLimit = 0;
                }else{
                    sharedPref.lowerLimit = Integer.valueOf(LowerText.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        UpperText.setText(String.valueOf(sharedPref.upperLimit));
        UpperLimitSoundSwitch.setChecked(sharedPref.upperLimitSound);
        UpperLimitVibSwitch.setChecked(sharedPref.upperLimitVib);

        LowerText.setText(String.valueOf(sharedPref.lowerLimit));
        LowerLimitSoundSwitch.setChecked(sharedPref.lowerLimitSound);
        LowerLimitVibSwitch.setChecked(sharedPref.lowerLimitVib);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sharedPref.saveValues();
    }
}