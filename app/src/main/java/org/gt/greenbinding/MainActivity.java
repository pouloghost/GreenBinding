package org.gt.greenbinding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = Binders.inflateAndBind(this, R.layout.activity_main, new TestViewModel(), null, false);
        setContentView(view);
    }
}
