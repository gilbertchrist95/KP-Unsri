package kpunsri.telkomsel.activities;

import android.content.Intent;
import android.os.AsyncTask;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.extras.UrlEndpoints;
import kpunsri.telkomsel.telegram.MyTelegram;


public class FormResponse extends AppCompatActivity {

    TextView bscRncName, btsName, siteId, cellID, alarmName, alarmType, severity, alarmTime, alarmId, alarmTriggeredBy, alarmStatus;
    TextView nodeBId, rncId, nodeBName, alarmCause;

    CheckBox batteryStolen, batteryBroken, gensetBroken, atsBroken, solarEmpty, sewaDaya, gensetOnly, lvd, backplane, rectrifierModul, controlModul, activity, communityProblem, overTemp, feederStollen, modulProblem, waitingModul, vswrHigh, interference, fading, jasuka, metroE, fots, idr, transmisiSdh, transmisiFailed, noIsr;
    StringBuffer resultCheckBox = new StringBuffer();
    Button kirimResult;
    public static String columnUpdateId = "UpdateIdfield";
    public static String receiveUpdateId = null;


    public static String columnbscRncName = "bsc";
    public static String columnbtsName = "bts";
    public static String columnsiteId = "site";
    public static String columncellID = "cell";
    public static String columnalarmName = "alarmname";
    public static String columnalarmType = "alarmtype";
    public static String columnseverity = "severity";
    public static String columnAlarmTime = "alarmtime";
    public static String columnalarmId = "alarmid";
    public static String columnalarmTriggeredBy = "alarmtriggeredby";
    public static String columnalarmStatus = "alarmstatus";
    public static String columnnodeBId = "nodebid";
    public static String columnrncId = "rncid";
    public static String columnnodeBName = "nodebname";
    public static String columnalarmCause = "alarmcause";

    public static String receivebscRncName = null;
    public static String receivebtsName = null;
    public static String receiveSiteId = null;
    public static String receivecellID = null;
    public static String receivealarmName = null;
    public static String receivealarmType = null;
    public static String receiveseverity = null;
    public static String receiveAlarmTime = null;
    public static String receivealarmId = null;
    public static String receivealarmTriggeredBy = null;
    public static String receivealarmStatus = null;
    public static String receivenodeBId = null;
    public static String receiverncId = null;
    public static String receivenodeBName = null;
    public static String receivealarmCause = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        initialTextView();
        initialCheckBox();
        kirimResult = (Button) findViewById(R.id.button_Kirim_Response);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        receiveData();
        kirimResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCheckBox.append(receivebtsName+" ");
                if (batteryStolen.isChecked()) {
                    resultCheckBox.append("Battery_Stolen ");
                }
                if (batteryBroken.isChecked()) {
                    resultCheckBox.append("Battery_Broken ");
                }
                if (gensetBroken.isChecked()) {
                    resultCheckBox.append("Genset_Broken ");
                }
                if (atsBroken.isChecked()) {
                    resultCheckBox.append("ATS_Broken ");
                }
                if (solarEmpty.isChecked()) {
                    resultCheckBox.append("Solar_Empty ");
                }
                if (sewaDaya.isChecked()) {
                    resultCheckBox.append("Sewa_Daya ");
                }
                if (gensetOnly.isChecked()) {
                    resultCheckBox.append("Genset_Only ");
                }
                if (lvd.isChecked()) {
                    resultCheckBox.append("LVD ");
                }
                if (backplane.isChecked()) {
                    resultCheckBox.append("Backplane ");
                }
                if (rectrifierModul.isChecked()) {
                    resultCheckBox.append("Rectifier_Modul ");
                }
                if (controlModul.isChecked()) {
                    resultCheckBox.append("Control_Modul ");
                }
                if (activity.isChecked()) {
                    resultCheckBox.append("Activity ");
                }
                if (communityProblem.isChecked()) {
                    resultCheckBox.append("Community ");
                }
                if (overTemp.isChecked()) {
                    resultCheckBox.append("Overtemp ");
                }
                if (feederStollen.isChecked()) {
                    resultCheckBox.append("Feeder_Stolen ");
                }
                if (modulProblem.isChecked()) {
                    resultCheckBox.append("Modul_Problem ");
                }
                if (waitingModul.isChecked()) {
                    resultCheckBox.append("Waiting_Modul ");
                }
                if (vswrHigh.isChecked()) {
                    resultCheckBox.append("VSWR_High ");
                }
                if (interference.isChecked()) {
                    resultCheckBox.append("Interference ");
                }
                if (fading.isChecked()) {
                    resultCheckBox.append("Fading ");
                }
                if (jasuka.isChecked()) {
                    resultCheckBox.append("Jasuka ");
                }
                if (metroE.isChecked()) {
                    resultCheckBox.append("Metro_E ");
                }
                if (fots.isChecked()) {
                    resultCheckBox.append("Fots ");
                }
                if (idr.isChecked()) {
                    resultCheckBox.append("IDR ");
                }
                if (transmisiSdh.isChecked()) {
                    resultCheckBox.append("Tranmisi_SDH ");
                }
                if (transmisiFailed.isChecked()) {
                    resultCheckBox.append("Transmisi_Failed ");
                }
                if (noIsr.isChecked()) {
                    resultCheckBox.append("No_ISR(Balmon) ");
                }

                String sendPesan = UrlEndpoints.URL_BOT_API + MyTelegram.BOT_API_KEY_TELEGRAM + UrlEndpoints.URL_SEND_MESSAGES;
                String chatId = "chat_id=";
                String text = "174288459&text=".concat(resultCheckBox.toString());

                String concatUrl = sendPesan + chatId + text;
                new SendResponse().execute(concatUrl);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id  = item.getItemId();

        if (id  == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialTextView() {
        bscRncName = (TextView) findViewById(R.id.textView_bsc_rnc_name);
        btsName = (TextView) findViewById(R.id.textView_bts_name);
        siteId = (TextView) findViewById(R.id.textView_site_id);
        cellID = (TextView) findViewById(R.id.textView_cell_id);
        alarmName = (TextView) findViewById(R.id.textView_alarm_name);
        alarmType = (TextView) findViewById(R.id.textView_alarm_type);
        severity = (TextView) findViewById(R.id.textView_severity);
        alarmTime = (TextView) findViewById(R.id.textView_alarm_time);
        alarmId = (TextView) findViewById(R.id.textView_alarm_id);
        alarmTriggeredBy = (TextView) findViewById(R.id.textView_alarm_triggered_by);
        alarmStatus = (TextView) findViewById(R.id.textView_alarm_status);

        nodeBId = (TextView) findViewById(R.id.textView_node_b_id);
        rncId = (TextView) findViewById(R.id.textView_rnc_id);
        nodeBName = (TextView) findViewById(R.id.textView_node_b_name);
        alarmCause = (TextView) findViewById(R.id.textView_alarm_cause);
    }

    private void initialCheckBox() {
        batteryStolen = (CheckBox) findViewById(R.id.batterystolen);
        batteryBroken = (CheckBox) findViewById(R.id.batterybroken);
        gensetBroken = (CheckBox) findViewById(R.id.gensetbroken);
        atsBroken = (CheckBox) findViewById(R.id.atsbroken);
        solarEmpty = (CheckBox) findViewById(R.id.solarempty);
        sewaDaya = (CheckBox) findViewById(R.id.sewadaya);
        gensetOnly = (CheckBox) findViewById(R.id.gensetonly);
        lvd = (CheckBox) findViewById(R.id.lvd);
        backplane = (CheckBox) findViewById(R.id.backplane);
        rectrifierModul = (CheckBox) findViewById(R.id.rectifiermodul);
        controlModul = (CheckBox) findViewById(R.id.controlmodul);
        activity = (CheckBox) findViewById(R.id.activity);
        communityProblem = (CheckBox) findViewById(R.id.community);
        overTemp = (CheckBox) findViewById(R.id.overtemp);
        feederStollen = (CheckBox) findViewById(R.id.feeder);
        modulProblem = (CheckBox) findViewById(R.id.modulproblem);
        waitingModul = (CheckBox) findViewById(R.id.waitingmodul);
        vswrHigh = (CheckBox) findViewById(R.id.vswr);
        interference = (CheckBox) findViewById(R.id.interference);
        fading = (CheckBox) findViewById(R.id.fading);
        jasuka = (CheckBox) findViewById(R.id.jasuka);
        metroE = (CheckBox) findViewById(R.id.metroe);
        fots = (CheckBox) findViewById(R.id.fots);
        idr = (CheckBox) findViewById(R.id.idr);
        transmisiSdh = (CheckBox) findViewById(R.id.tranmisisdh);
        transmisiFailed = (CheckBox) findViewById(R.id.transmisifailed);
        noIsr = (CheckBox) findViewById(R.id.balmon);
    }

    protected void receiveData() {
        receiveUpdateId = getIntent().getStringExtra(columnUpdateId);
        receivebscRncName = getIntent().getStringExtra(columnbscRncName);
        receivebtsName = getIntent().getStringExtra(columnbtsName);
        receiveSiteId = getIntent().getStringExtra(columnsiteId);
        receivecellID = getIntent().getStringExtra(columncellID);
        receivealarmName = getIntent().getStringExtra(columnalarmName);
        receivealarmType = getIntent().getStringExtra(columnalarmType);
        receiveseverity = getIntent().getStringExtra(columnseverity);
        receiveAlarmTime = getIntent().getStringExtra(columnAlarmTime);
        receivealarmId = getIntent().getStringExtra(columnalarmId);
        receivealarmTriggeredBy = getIntent().getStringExtra(columnalarmTriggeredBy);
        receivealarmStatus = getIntent().getStringExtra(columnalarmStatus);
        receivenodeBId = getIntent().getStringExtra(columnnodeBId);
        receiverncId = getIntent().getStringExtra(columnrncId);
        receivenodeBName = getIntent().getStringExtra(columnnodeBName);
        receivealarmCause = getIntent().getStringExtra(columnalarmCause);

        setText();
    }

    protected void setText() {
        bscRncName.setText(receivebscRncName);
        btsName.setText(receivebtsName);
        siteId.setText(receiveSiteId);
        cellID.setText(receivecellID);
        alarmName.setText(receivealarmName);
        alarmType.setText(receivealarmType);
        severity.setText(receiveseverity);
        alarmTime.setText(receiveAlarmTime);
        alarmId.setText(receivealarmId);
        alarmTriggeredBy.setText(receivealarmTriggeredBy);
        alarmStatus.setText(receivealarmStatus);
        nodeBId.setText(receivenodeBId);
        rncId.setText(receiverncId);
        nodeBName.setText(receivenodeBName);
        alarmCause.setText(receivealarmStatus);
    }

    private class SendResponse extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //do your request in here so that you don't interrupt the UI thread
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Here you are getDataInBackground with the task
            if (result.equals("200")) {
                Toast.makeText(FormResponse.this, "BERHASIL", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ActivityMain.class);
                startActivity(intent);

            } else {
                Toast.makeText(FormResponse.this, result, Toast.LENGTH_LONG).show();
            }

        }
    }

    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
//        int length = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("TAG", "The response is: " + response);
//            is = conn.getInputStream();
            // Convert the InputStream into a string
//            String contentAsString = convertInputStreamToString(is, length);

            return String.valueOf(response);

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

//    private String convertInputStreamToString(InputStream stream, int length) throws IOException {
//        Reader reader = null;
//        reader = new InputStreamReader(stream, "UTF-8");
//        char[] buffer = new char[length];
//        reader.read(buffer);
//        return new String(buffer);
//    }

//    private void makePostRequest(){
//        HttpClient httpClient = new DefaultHttpClient();
//        String url = UrlEndpoints.URL_BOT_API+ MyTelegram.BOT_API_KEY_TELEGRAM;
//        String chatId = "?chat_id=​174288459";
//        String text = "&text=".concat(resultCheckBox.toString());
//        String message = chatId.concat(text);
//        String urlMessage = url.concat(message);
////        HttpPost httpPost = new HttpPost(urlMessage);
//        HttpGet getUrl = new HttpGet(urlMessage);
//        try {
//            HttpResponse httpEntity = httpClient.execute(getUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.d("url",urlMessage);
//
//    }

}
