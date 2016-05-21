package kpunsri.telkomsel.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.GetMitraCallBack;
import kpunsri.telkomsel.pojo.Monitor;
import kpunsri.telkomsel.pojo.ServerRequestMitra;

public class FormResponseMonitor extends AppCompatActivity {

    private static long idIn, idOut;
    TextView status;
    TextView capacity, resultCapacity, lastTime, resultLastTime, bandwidth, min, minIn, resultMinIn, minOut,
            resultMinOut, max, maxIn, resultMaxIn, maxOut, resultMaxOut, average, averageIn, resultAverageIn,
            averageOut, resultAverageOut;
    private String  Capacity, LastTime, MinIn, AverageIn, MaxIn, MinOut, AverageOut, MaxOut;
    private DecimalFormat df = new DecimalFormat("##.##");
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_response_mitra);

        status = (TextView) findViewById(R.id.text_view_status);
        status.setVisibility(View.INVISIBLE);
        initialView();
        Intent i = getIntent();

        getSupportActionBar().setTitle(i.getStringExtra("namaMonitor"));
        Capacity = i.getStringExtra("capacity");
        LastTime = i.getStringExtra("resultLastTime");
        MinIn = i.getStringExtra("resultMinIn");
        AverageIn = i.getStringExtra("resultAverageIn");
        MaxIn = i.getStringExtra("resultMaxIn");
        MinOut = i.getStringExtra("resultMinOut");
        AverageOut = i.getStringExtra("resultAverageOut");
        MaxOut = i.getStringExtra("resultMaxOut");

        parseDataMonitor();
//        String nameMitra = i.getStringExtra("namaMitra");

//
//
//       ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            status.setVisibility(View.INVISIBLE);
//            setIdInOut(nameMitra);
//            Monitor mitraIn = new Monitor(Long.toString(idIn));
//            setDataIn(mitraIn);
//            Monitor mitraOut = new Monitor(Long.toString(idOut));
//            setDataOut(mitraOut);
//
//        } else {
//            status.setText("Network is not connected");
//            setViewInvisible();
//        }
    }

    private void parseDataMonitor() {
        resultCapacity.setText(Capacity);
        resultLastTime.setText(LastTime);
        resultMinIn.setText(MinIn);
        resultAverageIn.setText(AverageIn);
        resultMaxIn.setText(MaxIn);
        resultMinOut.setText(MinOut);
        resultAverageOut.setText(AverageOut);
        resultMaxOut.setText(MaxOut);
    }

    private void showMessageError() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FormResponseMonitor.this);
        dialogBuilder.setMessage("Data is null");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void initialView() {
        capacity = (TextView) findViewById(R.id.text_view_capacity);
        resultCapacity = (TextView) findViewById(R.id.text_view_result_capacity);
        lastTime = (TextView) findViewById(R.id.text_view_last_time);
        resultLastTime = (TextView) findViewById(R.id.text_view_result_lasttime);
        bandwidth = (TextView) findViewById(R.id.text_view_bandwidth);
        min = (TextView) findViewById(R.id.text_view_min);
        minIn = (TextView) findViewById(R.id.text_view_min_in);
        resultMinIn = (TextView) findViewById(R.id.text_view_result_min_in);
        minOut = (TextView) findViewById(R.id.text_view_min_out);
        resultMinOut = (TextView) findViewById(R.id.text_view_result_min_out);
        max = (TextView) findViewById(R.id.text_view_max);
        maxIn = (TextView) findViewById(R.id.text_view_max_in);
        resultMaxIn = (TextView) findViewById(R.id.text_view_result_max_in);
        maxOut = (TextView) findViewById(R.id.text_view_max_out);
        resultMaxOut = (TextView) findViewById(R.id.text_view_result_max_out);
        average = (TextView) findViewById(R.id.text_view_average);
        averageIn = (TextView) findViewById(R.id.text_view_average_in);
        resultAverageIn = (TextView) findViewById(R.id.text_view_result_average_in);
        averageOut = (TextView) findViewById(R.id.text_view_average_out);
        resultAverageOut = (TextView) findViewById(R.id.text_view_result_average_out);
    }

    private void setIdInOut(String nameMitra) {
        switch (nameMitra) {
            case "Alang–AlangLebar":
                idIn = 37662;
                idOut = 37668;
                break;
            case "Bandar Jaya":
                idIn = 36996;
                idOut = 37015;
                break;
            case "Basuki Rahmat":
                idIn = 33806;
                idOut = 34250;
                break;
            case "Baturaja":
                idIn = 33802;
                idOut = 34246;
                break;
            case "Belitang":
                idIn = 37992;
                idOut = 37998;
                break;
            case "Bengkulu Indah Mall":
                idIn = 33812;
                idOut = 34256;
                break;
            case "Betung":
                idIn = 33804;
                idOut = 34248;
                break;
            case "Curup":
                idIn = 37566;
                idOut = 37572;
                break;
            case "Indralaya":
                idIn = 37854;
                idOut = 37860;
                break;
            case "Jambi Inner":
                idIn = 33814;
                idOut = 34258;
                break;
            case "Kalianda":
                idIn = 34268;
                idOut = 34274;
                break;
            case "Kayu Agung":
                idIn = 34292;
                idOut = 34304;
                break;
            case "Kedaton":
                idIn = 37027;
                idOut = 37032;
                break;
            case "Kenten":
                idIn = 37614;
                idOut = 37620;
                break;
            case "Kotabumi":
                idIn = 37806;
                idOut = 37812;
                break;
            case "Kuala Tungkai":
                idIn = 37630;
                idOut = 37636;
                break;
            case "Manna":
                idIn = 36994;
                idOut = 37012;
                break;
            case "MDP":
                idIn = 33797;
                idOut = 34241;
                break;
            case "Merangin":
                idIn = 37534;
                idOut = 37540;
                break;
            case "Metro":
                idIn = 37598;
                idOut = 37604;
                break;
            case "Muara Enim":
                idIn = 37838;
                idOut = 37844;
                break;
            case "Muntok":
                idIn = 37960;
                idOut = 37966;
                break;
            case "Natar":
                idIn = 37042;
                idOut = 37049;
                break;
            case "Palembang Square":
                idIn = 33808;
                idOut = 34252;
                break;
            case "Prabumulih":
                idIn = 37976;
                idOut = 37982;
                break;
            case "Pringsewu":
                idIn = 37678;
                idOut = 37684;
                break;
            case "PS":
                idIn = 33803;
                idOut = 34246;
                break;
            case "Raden Inten":
                idIn = 37646;
                idOut = 37652;
                break;
            case "Sarolangun":
                idIn = 37822;
                idOut = 37828;
                break;
            case "Sebrang Ulu":
                idIn = 33810;
                idOut = 34254;
                break;
            case "Sekayu":
                idIn = 34294;
                idOut = 34306;
                break;
            case "Sribawono":
                idIn = 37944;
                idOut = 37950;
                break;
            case "Sungai Liat":
                idIn = 37582;
                idOut = 37588;
                break;
            case "Sungai Penuh":
                idIn = 37928;
                idOut = 37935;
                break;
            case "Teluk Betung":
                idIn = 37550;
                idOut = 37557;
                break;
            case "Tulang Bawang":
                idIn = 37694;
                idOut = 37700;
                break;
        }
    }
    private void setDataOut(Monitor mitraOut) {
        ServerRequestMitra serverRequestMitra = new ServerRequestMitra(this);
        serverRequestMitra.fetchMitraDataInBackground(mitraOut, new GetMitraCallBack() {
            @Override
            public void done(Monitor returnedMitra) {
                if (returnedMitra == null) {
                    showMessageError();
                } else {
                    resultMinOut.setText(df.format(returnedMitra.getValueMin()) + " Mbps");
                    resultAverageOut.setText(df.format(returnedMitra.getValueAvg()) + " Mbps");
                    resultMaxOut.setText(df.format(returnedMitra.getValue_max()) + " Mbps");
                }
            }
        });
    }
    private void setDataIn(Monitor mitraIn) {
        ServerRequestMitra serverRequestMitra = new ServerRequestMitra(this);
        serverRequestMitra.fetchMitraDataInBackground(mitraIn, new GetMitraCallBack() {
            @Override
            public void done(Monitor returnedMitra) {
                if (returnedMitra == null) {
                    showMessageError();
                } else {
                    resultLastTime.setText(returnedMitra.getClock());
                    resultMinIn.setText(df.format(returnedMitra.getValueMin()) + " Mbps");
                    resultAverageIn.setText(df.format(returnedMitra.getValueAvg()) + " Mbps");
                    resultMaxIn.setText(df.format(returnedMitra.getValue_max()) + " Mbps");
                }
            }
        });
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
