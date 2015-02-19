package com.example.Trakt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created with IntelliJ IDEA.
 * User: Andy
 * Date: 2/11/15
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityWithNoTitleBar extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

}
