package ch.jiikuy.velocitycalculator;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    static final int REQUEST_ViDEO_OPEN = 2;
    static final String EXTRA_VIDEO = "ch.jiikuy.velocitycalculator.VIDEO";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recordVideo(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void openVideo(View view) {
        Intent openVideoIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        openVideoIntent.addCategory(Intent.CATEGORY_OPENABLE);
        openVideoIntent.setType("video/*");
        startActivityForResult(openVideoIntent, REQUEST_ViDEO_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK || requestCode == REQUEST_ViDEO_OPEN && resultCode == RESULT_OK) {
            Uri videoUri = null;
            if(resultData != null) {
                videoUri = resultData.getData();
                Log.i("Uri ", videoUri.toString());
            }
            Intent calc = new Intent(this, CalculateActivity.class);
            calc.putExtra(EXTRA_VIDEO, videoUri);
            startActivity(calc);
        }
    }
}
