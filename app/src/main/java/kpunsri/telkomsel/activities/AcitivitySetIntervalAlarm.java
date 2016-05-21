package kpunsri.telkomsel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import kpunsri.telkomsel.R;


public class AcitivitySetIntervalAlarm extends AppCompatActivity {

    public static int progress = 5;
    private SeekBar seekBarIntervalAlarm;
    private TextView textViewIntervalAlarm;
    private Button buttonResultInterval;

    public static String resultInterval;

    @Override
    protected void onCreate(Bundle icicle) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(icicle);
        setContentView(R.layout.activity_setalarm);

        seekBarIntervalAlarm = (SeekBar) findViewById(R.id.seekBarIndicatorAlarm);
        textViewIntervalAlarm = (TextView) findViewById(R.id.textViewIndicatorAlarm);
        buttonResultInterval = (Button) findViewById(R.id.buttonResultInterval);

        seekBarIntervalAlarm.setProgress(Integer.getInteger(resultInterval, progress));

        textViewIntervalAlarm.setText(seekBarIntervalAlarm.getProgress() + " menit");

        seekBarIntervalAlarm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textViewIntervalAlarm.setText(seekBarIntervalAlarm.getProgress() + " menit");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewIntervalAlarm.setText(seekBarIntervalAlarm.getProgress() + " menit");

            }
        });

        buttonResultInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultInterval = textViewIntervalAlarm.getText().toString();
                finish();
            }
        });

    }

    public static Integer getIntervalAlarm() {
        return progress;
    }


}