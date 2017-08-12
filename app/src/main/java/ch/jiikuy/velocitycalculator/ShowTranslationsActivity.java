package ch.jiikuy.velocitycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ShowTranslationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_translations);
        try {
            getSupportActionBar().setTitle(getString(R.string.about_translations));
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml(getString(R.string.html_translations)));

    }
}
