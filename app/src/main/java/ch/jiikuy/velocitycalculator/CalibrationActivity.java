package ch.jiikuy.velocitycalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalibrationActivity extends AppCompatActivity {
    static double calibrationWidth = 30;
    static double calibrationDistance = 24.5;
    static double PX_PER_CM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
    }

    public void calibrate(View view) {
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        EditText editText3 = (EditText)findViewById(R.id.editText3);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CalibrationActivity.this);
        if(!editText2.getText().toString().isEmpty() && !editText3.getText().toString().isEmpty()) {
            calibrationWidth = Double.parseDouble(editText2.getText().toString());
            calibrationDistance = Double.parseDouble(editText3.getText().toString());
            final Intent goToMain = new Intent(this, MainActivity.class);
            alertDialogBuilder.setMessage(getString(R.string.text_calibrationsuccess))
                    .setCancelable(false)
                    .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(goToMain);
                        }
                    })
                    .show();


        }else {
            alertDialogBuilder.setMessage(R.string.text_notCalibrated)
            .show();

        }
    }
}
