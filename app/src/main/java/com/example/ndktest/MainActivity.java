package com.example.ndktest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.sample_text);
        btn.setText(stringFromJNI());
    }

    public native String stringFromJNI();
}