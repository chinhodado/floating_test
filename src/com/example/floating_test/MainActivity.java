package com.example.floating_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Just an activity to start the service from the launcher, and do nothing more
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(MainActivity.this, MyService.class));

        // immediately closes itself
        finish();
    }
}
