package com.example.floating_test;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyService extends Service {
    public static WindowManager windowManager;
    public static LinearLayout mainView;
    public WebView webView;
    public EditText edt;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        // main view
        mainView = (LinearLayout) inflater.inflate(R.layout.popup, null);
        WindowManager.LayoutParams mainViewParams = new WindowManager.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        windowManager.addView(mainView, mainViewParams);

        // main view - web views
        webView = (WebView) mainView.findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL("", "<p>hello world in webview</p>", "text/html", "UTF-8", "");

        // main view - invisible top section
        View top = (View) mainView.findViewById(R.id.dialog_top);
        top.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
