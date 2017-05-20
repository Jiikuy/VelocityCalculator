package ch.jiikuy.velocitycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    }
}
