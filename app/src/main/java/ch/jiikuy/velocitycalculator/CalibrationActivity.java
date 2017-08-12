package ch.jiikuy.velocitycalculator;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalibrationActivity extends AppCompatActivity {
    static double calibrationWidth = 0;
    static double calibrationDistance = 0;
    static double PX_PER_CM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
    }

    public void calibrate(View view) {
        // Show calibration screen
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        EditText editText3 = (EditText)findViewById(R.id.editText3);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        try {
            if (!editText2.getText().toString().isEmpty() && !editText3.getText().toString().isEmpty()) {
                // Save valuesget vid
                calibrationWidth = Double.parseDouble(editText2.getText().toString());
                calibrationDistance = Double.parseDouble(editText3.getText().toString());
                editor.putLong(getString(R.string.calibration_distance_key), Double.doubleToLongBits(calibrationDistance));
                editor.putLong(getString(R.string.calibration_width_key), Double.doubleToLongBits(calibrationWidth));
                editor.apply();
                alertDialogBuilder.setMessage(getString(R.string.text_calibrationsuccess))
                        .setCancelable(false)
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();


            } else {
                alertDialogBuilder.setMessage(R.string.text_notCalibrated)
                        .show();

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            alertDialogBuilder.setMessage(R.string.text_notCalibrated)
                    .show();
        }
    }
}
