package kpunsri.telkomsel.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import kpunsri.telkomsel.R;

/**
 * Created by Gilbert on 12/29/2015.
 */
public class ListIntervalAlarm extends DialogFragment {
    public static int intervalAlarmTime;
    final CharSequence interval[] = new CharSequence[] {"0 menit","1 menit", "3 menit", "5 menit"};

    @Override
    public Dialog onCreateDialog(Bundle saved){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.tekssetalarm).setSingleChoiceItems(interval, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        intervalAlarmTime = 0;
                        break;
                    case 1:
                        intervalAlarmTime = 1;
                        break;
                    case 2:
                        intervalAlarmTime = 3;
                        break;
                    case 3:
                        intervalAlarmTime = 5;
                        break;
                    default:
                        break;
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Interval time: "+intervalAlarmTime+" menit",Toast.LENGTH_LONG).show();
            }
        });


        return super.onCreateDialog(saved);
    }
}
