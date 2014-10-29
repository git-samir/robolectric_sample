package robolectric.example.com.initrobolectric;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SecondActivityTest {

    private SecondActivity activity;
    private ImageView imageView;
    private TextView textView;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SecondActivity.class).create().get();
        imageView = (ImageView) activity.findViewById(R.id.imageView);
        textView = (TextView) activity.findViewById(R.id.textView);
    }

    @Test
    public void shouldHaveALogo() throws Exception {
        assertThat(imageView.getVisibility(), equalTo(View.VISIBLE));
        assertThat(shadowOf(imageView.getDrawable()).getCreatedFromResId(),
                equalTo(R.drawable.robolectric));
    }

    @Test
    public void testTextView_shouldHaveCorrectText() throws Exception{
        String expectedText = textView.getText().toString();
        String actualText = activity.getResources().getString(R.string.welcome_second);
        assertThat(expectedText, equalTo(actualText));
        // you can also use org.junit.Assert.assertEquals to check if two objects are same or not.
        assertEquals(expectedText, actualText);
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
        imageView = null;
        textView = null;
    }
}
