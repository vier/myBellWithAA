package net.monoboy.test;

import com.googlecode.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.os.Bundle;

@EActivity(R.layout.main)
public class HelloAndroidActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

