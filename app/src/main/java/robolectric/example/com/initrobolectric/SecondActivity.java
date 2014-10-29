package robolectric.example.com.initrobolectric;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by samir on 10/29/14.
 */
public class SecondActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
