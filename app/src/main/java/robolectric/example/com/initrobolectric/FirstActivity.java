package robolectric.example.com.initrobolectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FirstActivity extends ActionBarActivity implements View.OnClickListener {

    private Button buttonChangeText;
    private Button buttonShowToast;
    private Button buttonStartNext;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        buttonChangeText = (Button) findViewById(R.id.button_change_text);
        buttonChangeText.setOnClickListener(this);
        buttonShowToast = (Button) findViewById(R.id.button_show_toast);
        buttonShowToast.setOnClickListener(this);
        buttonStartNext = (Button) findViewById(R.id.button_start_second_activity);
        buttonStartNext.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_change_text:
                textView.setText(R.string.changed_text);
                break;
            case R.id.button_show_toast:
                Toast.makeText(FirstActivity.this, R.string.hello_toast, Toast.LENGTH_LONG).show();
                break;
            case R.id.button_start_second_activity:
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                break;
        }
    }
}
