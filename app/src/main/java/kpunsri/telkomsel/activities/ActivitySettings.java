package kpunsri.telkomsel.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import kpunsri.telkomsel.R;

public class ActivitySettings extends AppCompatActivity{
    Toolbar toolbar;
    ListView simpleListView;

    public static boolean autoLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        simpleListView = (ListView)findViewById(R.id.simple_List_View);
        String[] itemLayanan  = new String[] {"Set Alarm", "Auto Logout"};

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemLayanan);
        simpleListView.setAdapter(listAdapter);

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(getApplicationContext(), AcitivitySetIntervalAlarm.class);
                    startActivity(intent);
                } else if (i == 1) {
                    AutoLogout();
                }
            }
        });


    }

    public void AutoLogout() {
        new AlertDialog.Builder(this)
                .setMessage("Logout when exit?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1) {
                        autoLogout = true;
                        finish();
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        autoLogout = true;
                        finish();
                    }
                }).create().show();
    }

}
