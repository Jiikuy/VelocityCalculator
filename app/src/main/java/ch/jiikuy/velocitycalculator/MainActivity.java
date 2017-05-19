package ch.jiikuy.velocitycalculator;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Start calibration activity
        int id = item.getItemId();
        if(id == R.menu.main_menu) {
            Intent intent = new Intent(this, CalibrationActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void recordVideo(View view) {
        // Capture video
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void openVideo(View view) {
        // Open video
        Intent openVideoIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        openVideoIntent.addCategory(Intent.CATEGORY_OPENABLE);
        openVideoIntent.setType("video/*");
        startActivityForResult(openVideoIntent, REQUEST_ViDEO_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // Start CalculationActivity
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
