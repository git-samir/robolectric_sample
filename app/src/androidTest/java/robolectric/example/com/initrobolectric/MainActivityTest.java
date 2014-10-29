package robolectric.example.com.initrobolectric;

import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static java.lang.Exception.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest{

    private MainActivity mainActivity;

    /**
     The @Before methods will be run before every test case,
     so will probably be run multiple times during a test run.
     @throws Exception.
     */
    @Before
    public void setUp() throws Exception {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void clickingButtonShouldChangeInitialText() {
        mainActivity.findViewById(R.id.button_change_text).performClick();
        String changedText = ((TextView) mainActivity.findViewById(R.id.textView)).getText().toString();
        String expectedText = mainActivity.getResources().getString(R.string.changed_text);
        assertThat(expectedText, equalTo(changedText));
    }

    @Test
    public void clickingButtonShouldShowToast() {
        mainActivity.findViewById(R.id.button_show_toast).performClick();
        String toastShown = ShadowToast.getTextOfLatestToast();
        String toastExpected = mainActivity.getResources().getString(R.string.hello_toast);
        assertThat(toastExpected, equalTo(toastShown));
    }


    /**
     The @After methods will be run after every test case,
     so will probably be run multiple times during a test run.
     @throws Exception.
     */
    @After
    public void tearDown() throws Exception {

    }
}