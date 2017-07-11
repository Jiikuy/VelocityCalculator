package ch.jiikuy.velocitycalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int REQUEST_ViDEO_OPEN = 2;
    static final String EXTRA_VIDEO = "ch.jiikuy.velocitycalculator.VIDEO";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Get calibration values
        CalibrationActivity.calibrationDistance = Double.longBitsToDouble(sharedPreferences.getLong(getString(R.string.calibration_distance_key), 0));
        CalibrationActivity.calibrationWidth = Double.longBitsToDouble(sharedPreferences.getLong(getString(R.string.calibration_width_key), 0));
        if(CalibrationActivity.calibrationDistance == 0 && CalibrationActivity.calibrationWidth == 0) {
            // Show AlertDialog to calibrate app
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage(getString(R.string.text_calibrationneeded))
                    .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, CalibrationActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Show calibration ActionBar item
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Start calibration activity
        int id = item.getItemId();
        if(id == R.id.action_calibrate) {
            Intent intent = new Intent(this, CalibrationActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
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
            }
            Intent calc = new Intent(this, CalculateActivity.class);
            calc.putExtra(getResources().getString(R.string.main_extra_video), videoUri);
            startActivity(calc);
        }
    }
}
