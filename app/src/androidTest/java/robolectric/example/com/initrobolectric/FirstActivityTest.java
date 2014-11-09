package robolectric.example.com.initrobolectric;

import android.content.Intent;
import android.widget.TextView;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import static java.lang.Exception.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class FirstActivityTest {

    private FirstActivity firstActivity;

    /**
     * The @Before methods will be run before every test case,
     * so will probably be run multiple times during a test run.
     *
     * @throws Exception.
     */
    @Before
    public void setUp() throws Exception {
        firstActivity = Robolectric.buildActivity(FirstActivity.class).create().get();
    }

    @Test
    public void testChangeTextButton_clickingShouldChangeInitialText() throws Exception {
        firstActivity.findViewById(R.id.button_change_text).performClick();
        String changedText = ((TextView) firstActivity.findViewById(R.id.textView)).getText().toString();
        String expectedText = firstActivity.getResources().getString(R.string.changed_text);
        assertThat(changedText, equalTo(expectedText));
    }

    @Test
    public void testShowToastButton_clickingShouldShowToast() throws Exception {
        firstActivity.findViewById(R.id.button_show_toast).performClick();
        String toastShown = ShadowToast.getTextOfLatestToast();
        String toastExpected = firstActivity.getResources().getString(R.string.hello_toast);
        assertThat(toastShown, equalTo(toastExpected));
    }

    @Test
    public void testStartSecondActivityButton_clickingShouldShowStartSecondActivity() throws Exception {
        firstActivity.findViewById(R.id.button_start_second_activity).performClick();
        ShadowActivity shadowActivity = shadowOf(firstActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat(startedIntent.getComponent().getClassName(), equalTo(SecondActivity.class.getName()));
    }


    /**
     * The @After methods will be run after every test case,
     * so will probably be run multiple times during a test run.
     *
     * @throws Exception.
     */
    @After
    public void tearDown() throws Exception {

    }
}