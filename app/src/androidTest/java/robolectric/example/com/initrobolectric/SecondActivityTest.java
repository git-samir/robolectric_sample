package robolectric.example.com.initrobolectric;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
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

import java.util.Locale;

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
        String actualText = textView.getText().toString();
        String expectedText = activity.getResources().getString(R.string.welcome_second);
        assertThat(expectedText, equalTo(actualText));
        // you can also use org.junit.Assert.assertEquals to check if two objects are same or not.
        assertEquals(expectedText, actualText);
    }

    @Test @Config(qualifiers = "en")
    public void testTextView_shouldHaveEnglishText() throws Exception{
        String actualText = textView.getText().toString();
        String expectedText = getLocaleResources("en").getString(R.string.welcome_second);

    }

    @Test @Config(qualifiers = "fr")
    public void testTextView_shouldHaveFrenchText() throws Exception{
        String actualText = textView.getText().toString();
        String expectedText = getLocaleResources("fr").getString(R.string.welcome_second);
    }

    private Resources getLocaleResources(String locale) {
        Configuration conf = activity.getResources().getConfiguration();
        conf.locale = new Locale("en");
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new Resources(activity.getAssets(), metrics, conf);
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
        imageView = null;
        textView = null;
    }
}
