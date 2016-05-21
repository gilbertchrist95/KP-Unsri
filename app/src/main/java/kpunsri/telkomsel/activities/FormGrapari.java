package kpunsri.telkomsel.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import java.text.DecimalFormat;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.GetGrapariCallBack;
import kpunsri.telkomsel.pojo.Monitor;
import kpunsri.telkomsel.pojo.ServerRequestGrapari;

public class FormGrapari extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    static String nearEnd, farEnd, typeOfCon;
    Spinner spinnerNearEnd, spinnerFarEnd, spinnerTypeOfCon; //belum
    private Button buttonOk;
    private long idGrapIn, idGrapOut;
    static String resultCapacity,resultLastTime,resultMinIn,resultAverageIn,resultMaxIn,resultMinOut,resultAverageOut,resultMaxOut;
    private DecimalFormat df = new DecimalFormat("##.##");


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_jaringan_grapari_telkomsel);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerNearEnd = (Spinner)findViewById(R.id.spinner_near_end);
        spinnerFarEnd = (Spinner)findViewById(R.id.spinner_far_end);
        spinnerTypeOfCon = (Spinner)findViewById(R.id.spinner_type_of_con);
        buttonOk = (Button)findViewById(R.id.button_grapari_result);

        spinnerNearEnd.setOnItemSelectedListener(this);
        spinnerFarEnd.setOnItemSelectedListener(this);
        spinnerTypeOfCon.setOnItemSelectedListener(this);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Grapari",nearEnd+" "+farEnd+" "+typeOfCon);

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    requestData(nearEnd,farEnd,typeOfCon);
                }else{
                    Toast.makeText(FormGrapari.this, "Network is not connected", Toast.LENGTH_SHORT).show();
                }


//                Intent i = new Intent(v.getContext(), ActivityResponseGrapari.class);
//                i.putExtra("nearEnd",nearEnd);
//                i.putExtra("farEnd",farEnd);
//                i.putExtra("typeOfCon",typeOfCon);
//                v.getContext().startActivity(i);
            }
        });

    }

    private void requestData(String nearEnd, String farEnd, String typeOfCon) {
        setIDInOut(nearEnd,farEnd,typeOfCon);
        setCapacity(typeOfCon);
        setDataIn(new Monitor(Long.toString(idGrapIn)));
        setDataOut(new Monitor(Long.toString(idGrapOut)));
    }

    private void setIDInOut(String nearEnd, String farEnd, String typeOfCon) {
        Log.d("Location",nearEnd+" "+farEnd+" "+typeOfCon);
        switch (nearEnd) {
            case "Palembang":
                switch (farEnd){
                    case "Belitung":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 24123;
                            idGrapOut = 24216;
                        } else {
                            idGrapIn = 24137;
                            idGrapOut = 24230;
                        }
                        break;
                    case "Bengkulu":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 24282;
                            idGrapOut = 24375;
                        } else {
                            idGrapIn = 24312;
                            idGrapOut = 24405;
                        }
                        break;
                    case "Lubuk Linggau":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 24962;
                            idGrapOut = 25055;
                        } else {
                            idGrapIn = 24973;
                            idGrapOut = 25066;
                        }
                        break;
                    case "Pangkal Pinang":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 25132;
                            idGrapOut = 25225;
                        } else {
                            idGrapIn = 25148;
                            idGrapOut = 25241;
                        }
                        break;
                    case "Palembang":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 25313;
                            idGrapOut = 25406;
                        } else {
                            idGrapIn = 25314;
                            idGrapOut = 25407;
                        }
                        break;
                    case "Regional":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 30957;
                            idGrapOut = 31039;
                        } else {
                            idGrapIn = 30969;
                            idGrapOut = 31051;
                        }
                        break;
                }
                break;
            case "Jambi":
                switch (farEnd){
                    case "Bungo":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 24477;
                            idGrapOut = 24570;
                        } else {
                            idGrapIn = 24467;
                            idGrapOut = 24560;
                        }
                        break;
                    case "Jambi":
                        if (typeOfCon == "MetroLite") {
                            idGrapIn = 24622;
                            idGrapOut = 24715;
                        } else {
                            Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }
                }
                break;
            case "Lampung":
                if (typeOfCon == "MetroLite") {
                    idGrapIn = 24792;
                    idGrapOut = 24885;
                } else {
                    idGrapIn = 24804;
                    idGrapOut = 24897;
                }
                break;
        }
        Log.d("In out",idGrapIn+" "+idGrapOut);
    }

    private void setCapacity(String typeOfCon) {
        if (typeOfCon.equals("MetroLite")) {
            resultCapacity="50 Mbps";
        } else if (typeOfCon.equals("2E1")) {
            resultCapacity="2 Mbps";
        }
    }

    private void setDataIn(Monitor monitor) {
        ServerRequestGrapari serverRequestGrapari = new ServerRequestGrapari(this);
        serverRequestGrapari.fetchGrapariDataInBackground(monitor, new GetGrapariCallBack() {
            @Override
            public void done(Monitor returnedGrapari) {
                if (returnedGrapari == null) {
                    showMessageError();
                } else {
                    setResultIn(returnedGrapari);

                }
            }
        });
    }

    private void setResultIn(Monitor monitor) {
        resultLastTime = monitor.getClock();
        resultMinIn = df.format(monitor.getValueMin()) + " Mbps";
        resultAverageIn = df.format(monitor.getValueAvg()) + " Mbps";
        resultMaxIn = df.format(monitor.getValue_max()) + " Mbps";
    }

    private void setDataOut(Monitor monitor) {
        ServerRequestGrapari serverRequestGrapari = new ServerRequestGrapari(this);
        serverRequestGrapari.fetchGrapariDataInBackground(monitor, new GetGrapariCallBack() {
            @Override
            public void done(Monitor returnedGrapari) {
                if (returnedGrapari == null) {
                    showMessageError();
                } else {
                    resultMinOut = df.format(returnedGrapari.getValueMin()) + " Mbps";
                    resultAverageOut = df.format(returnedGrapari.getValueAvg()) + " Mbps";
                    resultMaxOut = df.format(returnedGrapari.getValue_max()) + " Mbps";

                    intentToResponseMonitor();
                }
            }
        });
    }

    private void intentToResponseMonitor() {
        Intent i = new Intent(FormGrapari.this, FormResponseMonitor.class);
        i.putExtra("namaMonitor","Grapari");
        i.putExtra("capacity",resultCapacity);

        i.putExtra("resultLastTime",resultLastTime);
        i.putExtra("resultMinIn",resultMinIn);
        i.putExtra("resultAverageIn",resultAverageIn);
        i.putExtra("resultMaxIn",resultMaxIn);

        i.putExtra("resultMinOut",resultMinOut);
        i.putExtra("resultAverageOut",resultAverageOut);
        i.putExtra("resultMaxOut",resultMaxOut);

        startActivity(i);
    }


    private void showMessageError() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FormGrapari.this);
        dialogBuilder.setMessage("Data is null");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }


        @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //nearEnd = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.spinner_near_end:
                if (position == 0){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.grapari_far_palembang, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFarEnd.setAdapter(adapter);
                    nearEnd = parent.getItemAtPosition(position).toString();
                }
                else if (position == 1){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.grapari_far_jambi, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFarEnd.setAdapter(adapter);
                    nearEnd = parent.getItemAtPosition(position).toString();
                }
                else if (position ==2){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.grapari_far_lampung, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFarEnd.setAdapter(adapter);
                    nearEnd = parent.getItemAtPosition(position).toString();
                }
                break;
            case R.id.spinner_far_end:
                farEnd = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner_type_of_con:
                typeOfCon = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        nearEnd = "Palembang";
        farEnd = "Belitung";
        typeOfCon = "Metrolite";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id  = item.getItemId();

        if (id  == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }


}
