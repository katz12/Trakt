package com.example.Trakt;

import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: Andy
 * Date: 2/12/15
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TraktWebViewClient extends WebViewClient {
    private static final String TAG = TraktWebViewClient.class.getName();

    private final WeakReference<MainActivity> activityRef;
    private final Handler handler = new Handler();

    public TraktWebViewClient(final MainActivity mainActivity) {
        this.activityRef = new WeakReference<MainActivity>(mainActivity);
    }

    @Override
    public void onPageFinished(final WebView webView, final String url) {
        if (activityRef == null) {
            Log.e(TAG, "AH, lost main activity!");
            return;
        }

        final StringBuilder content = new StringBuilder(1000);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(activityRef.get().getAssets().open("js/main.js"), "UTF-8"));

            String mLine = reader.readLine();
            while (mLine != null) {
                content.append(mLine);
                mLine = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(MainActivity.class.getName(), "io exception: " + content.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "io exception closing");
                }
            }
        }
        Log.d(MainActivity.class.getName(), content.toString());
        runJavascript(webView, content.toString());
    }

    private void runJavascript(final WebView webView, final String source) {
        handler.post(new Runnable() {
            public void run() {
                Log.d(TAG, "HIIIII");
                webView.loadUrl("javascript:" + source);
            }
        });
    }
}
