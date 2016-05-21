package kpunsri.telkomsel.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.GetGrapariCallBack;

import kpunsri.telkomsel.pojo.Monitor;
import kpunsri.telkomsel.pojo.ServerRequestGrapari;

public class ActivityResponseGrapari extends AppCompatActivity {

    long idGrapIn, idGrapOut;
    TextView status;
    TextView capacity, resultCapacity, lastTime, resultLastTime, bandwidth, min, minIn, resultMinIn, minOut,
            resultMinOut, max, maxIn, resultMaxIn, maxOut, resultMaxOut, average, averageIn, resultAverageIn,
            averageOut, resultAverageOut;
    DecimalFormat df = new DecimalFormat("##.##");
    String  Capacity, LastTime, MinIn, AverageIn, MaxIn, MinOut, AverageOut, MaxOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_grapari);

        Intent i = getIntent();
        Capacity = i.getStringExtra("resultCapacity");
        LastTime = i.getStringExtra("resultLastTime");
        MinIn = i.getStringExtra("resultMinIn");
        AverageIn = i.getStringExtra("resultAverageIn");
        MaxIn = i.getStringExtra("resultMaxIn");
        MinOut = i.getStringExtra("resultMinOut");
        AverageOut = i.getStringExtra("resultAverageOut");
        MaxOut = i.getStringExtra("resultMaxOut");

//        String nearEnd = i.getStringExtra("nearEnd");
//        String farEnd = i.getStringExtra("farEnd");
//        String typeOfCon = i.getStringExtra("typeOfCon");
        //Log.d("ResponseGrapari",nearEnd+" "+farEnd+" "+typeOfCon);
        getSupportActionBar().setTitle("Grapari Telkomsel");


        status = (TextView) findViewById(R.id.text_view_grap_status);
        status.setVisibility(View.INVISIBLE);
        initialView();

        parseDataGrapari();

//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            status.setVisibility(View.INVISIBLE);
//            setIDInOut(nearEnd, farEnd, typeOfCon);
//            setCapacity(typeOfCon);
//            //Monitor grapariIn = new Monitor(Long.toString(idGrapIn));
//            setDataIn(new Monitor(Long.toString(idGrapIn)));
//            //Monitor grapariOut = new Monitor(Long.toString(idGrapOut));
//            setDataOut(new Monitor(Long.toString(idGrapOut)));
//
//        } else {
//            status.setText("Network is not connected");
//            setViewInvisible();
//        }
     }

    private void parseDataGrapari() {
        resultCapacity.setText(Capacity);
        resultLastTime.setText(LastTime);
        resultMinIn.setText(MinIn);
        resultAverageIn.setText(AverageIn);
        resultMaxIn.setText(MaxIn);
        resultMinOut.setText(MinOut);
        resultAverageOut.setText(AverageOut);
        resultMaxOut.setText(MaxOut);
    }

    private void initialView() {
        capacity = (TextView) findViewById(R.id.text_view_capacity);
        resultCapacity = (TextView) findViewById(R.id.text_view_result_grap_capacity);
        lastTime = (TextView) findViewById(R.id.text_view_last_time);
        resultLastTime = (TextView) findViewById(R.id.text_view_result_grap_lasttime);
        bandwidth = (TextView) findViewById(R.id.text_view_bandwidth);
        min = (TextView) findViewById(R.id.text_view_min);
        minIn = (TextView) findViewById(R.id.text_view_min_in);
        resultMinIn = (TextView) findViewById(R.id.text_view_result_grap_min_in);
        minOut = (TextView) findViewById(R.id.text_view_min_out);
        resultMinOut = (TextView) findViewById(R.id.text_view_result_grap_min_out);
        max = (TextView) findViewById(R.id.text_view_max);
        maxIn = (TextView) findViewById(R.id.text_view_max_in);
        resultMaxIn = (TextView) findViewById(R.id.text_view_result_grap_max_in);
        maxOut = (TextView) findViewById(R.id.text_view_max_out);
        resultMaxOut = (TextView) findViewById(R.id.text_view_result_grap_max_out);
        average = (TextView) findViewById(R.id.text_view_average);
        averageIn = (TextView) findViewById(R.id.text_view_average_in);
        resultAverageIn = (TextView) findViewById(R.id.text_view_result_grap_average_in);
        averageOut = (TextView) findViewById(R.id.text_view_average_out);
        resultAverageOut = (TextView) findViewById(R.id.text_view_result_grap_average_out);
    }

    private void setDataOut(Monitor monitor) {
        ServerRequestGrapari serverRequestGrapari = new ServerRequestGrapari(this);
        serverRequestGrapari.fetchGrapariDataInBackground(monitor, new GetGrapariCallBack() {
            @Override
            public void done(Monitor monitor) {
                if (monitor == null) {
                   // showMessageError();
                } else {
                   // setTextO(monitor);
                }
            }
        });
    }

    private void setTextO(Monitor monitor) {
        resultMinOut.setText(df.format(monitor.getValueMin()) + " Mbps");
        resultAverageOut.setText(df.format(monitor.getValueAvg()) + " Mbps");
        resultMaxOut.setText(df.format(monitor.getValue_max()) + " Mbps");
    }


    private void setDataIn(Monitor monitor) {
        ServerRequestGrapari serverRequestGrapari = new ServerRequestGrapari(this);
        serverRequestGrapari.fetchGrapariDataInBackground(monitor, new GetGrapariCallBack() {
            @Override
            public void done(Monitor monitor) {
                if (monitor == null) {
                    //showMessageError();
                } else {
                    //setText0(monitor);
                }
            }
        });
    }

    private void setText0(Monitor monitor) {
        resultLastTime.setText(monitor.getClock());
        resultMinIn.setText(df.format(monitor.getValueMin()) + " Mbps");
        resultAverageIn.setText(df.format(monitor.getValueAvg()) + " Mbps");
        resultMaxIn.setText(df.format(monitor.getValue_max()) + " Mbps");
    }

    private void showMessageError() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityResponseGrapari.this);
        dialogBuilder.setMessage("Data is null");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
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
                            Toast.makeText(ActivityResponseGrapari.this, "Something Wrong", Toast.LENGTH_SHORT).show();
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
            resultCapacity.setText("50 Mbps");
        } else if (typeOfCon.equals("2E1")) {
            resultCapacity.setText("2 Mbps");
        }
    }

    private void setViewInvisible() {
        capacity.setVisibility(View.INVISIBLE);
        resultCapacity.setVisibility(View.INVISIBLE);
        lastTime.setVisibility(View.INVISIBLE);
        resultLastTime.setVisibility(View.INVISIBLE);
        bandwidth.setVisibility(View.INVISIBLE);
        min.setVisibility(View.INVISIBLE);
        minIn.setVisibility(View.INVISIBLE);
        resultMinIn.setVisibility(View.INVISIBLE);
        minOut.setVisibility(View.INVISIBLE);
        resultMinOut.setVisibility(View.INVISIBLE);
        max.setVisibility(View.INVISIBLE);
        maxIn.setVisibility(View.INVISIBLE);
        resultMaxIn.setVisibility(View.INVISIBLE);
        maxOut.setVisibility(View.INVISIBLE);
        resultMinOut.setVisibility(View.INVISIBLE);
        average.setVisibility(View.INVISIBLE);
        averageIn.setVisibility(View.INVISIBLE);
        resultAverageIn.setVisibility(View.INVISIBLE);
        averageOut.setVisibility(View.INVISIBLE);
        resultAverageOut.setVisibility(View.INVISIBLE);
    }


}
