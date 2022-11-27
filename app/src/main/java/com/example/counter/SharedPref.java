package com.example.counter;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPref instance;
    public int value = 0;
    public int upperLimit;
    public boolean upperLimitSound;
    public boolean upperLimitVib;
    public int lowerLimit;
    public boolean lowerLimitSound;
    public boolean lowerLimitVib;

    private SharedPreferences sharedPreferences;
    private static final String KEY_VALUE="COUNTERVALUE";
    private static final String UPPER_VALUE="UPPERVALUE";
    private static final String LOWER_VALUE="LOWERVALUE";
    private static final String UPPERLIMIT_SOUND="UPPERLIMIT_SOUND";
    private static final String UPPERLIMIT_VIB="UPPERLIMIT_VIB";
    private static final String LOWERLIMIT_SOUND="LOWERLIMIT_SOUND";
    private static final String LOWERLIMIT_VIB="LOWERLIMIT_VIB";

    private SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        loadValues();
    }

    public static SharedPref getInstance(Context context){
        if(instance==null){
            instance = new SharedPref(context);
        }

        return instance;
    }

    private void loadValues(){
        value = sharedPreferences.getInt(KEY_VALUE, 0);
        upperLimit = sharedPreferences.getInt(UPPER_VALUE, 30);
        upperLimitSound= sharedPreferences.getBoolean(UPPERLIMIT_SOUND, true);
        upperLimitVib= sharedPreferences.getBoolean(UPPERLIMIT_VIB, true);
        lowerLimit = sharedPreferences.getInt(LOWER_VALUE, 0);
        lowerLimitSound= sharedPreferences.getBoolean(LOWERLIMIT_SOUND, true);
        lowerLimitVib= sharedPreferences.getBoolean(LOWERLIMIT_VIB, true);
    }

    public void saveValues(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_VALUE, value);
        editor.putInt(UPPER_VALUE, upperLimit);
        editor.putBoolean(UPPERLIMIT_SOUND, upperLimitSound);
        editor.putBoolean(UPPERLIMIT_VIB, upperLimitVib);
        editor.putInt(LOWER_VALUE, lowerLimit);
        editor.putBoolean(LOWERLIMIT_SOUND, lowerLimitSound);
        editor.putBoolean(LOWERLIMIT_VIB, lowerLimitVib);
        editor.commit();
    }
}
