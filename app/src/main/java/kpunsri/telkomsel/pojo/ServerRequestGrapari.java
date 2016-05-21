package kpunsri.telkomsel.pojo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Gilbert on 3/5/2016.
 */
public class ServerRequestGrapari {

    private ProgressDialog progressDialog;
    public static final int CONNECTION_TIME_Grapari = 10000 * 15;
    public static final String SERVER_ADDRESS = "http://alarmbot.comlu.com/";

    public ServerRequestGrapari(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait..");
    }

    public void fetchGrapariDataInBackground(Monitor grapari, GetGrapariCallBack callBack){
        progressDialog.show();
        new fetchGrapariDataAsyncTask(grapari,callBack).execute();
    }

    private class fetchGrapariDataAsyncTask extends AsyncTask<Void, Void, Monitor>{
        Monitor grapari;
        GetGrapariCallBack grapariCallBack;

        public fetchGrapariDataAsyncTask(Monitor grapari, GetGrapariCallBack callBack) {
            this.grapari = grapari;
            this.grapariCallBack = callBack;
        }

        @Override
        protected Monitor doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("itemid",grapari.itemid));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,CONNECTION_TIME_Grapari);
            HttpConnectionParams.setSoTimeout(httpRequestParams,CONNECTION_TIME_Grapari);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchGrapariData.php");

            Monitor monitor = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                String result = EntityUtils.toString(httpEntity);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() == 0){
                    monitor = null;
                }else{
                    String clock = jsonObject.getString("clock");
                    String num = jsonObject.getString("num");
                    String value_min = jsonObject.getString("value_min");
                    String value_avg = jsonObject.getString("value_avg");
                    String value_max = jsonObject.getString("value_max");

                    Log.d("clock",clock);
                    Log.d("valueMin", value_min);
                    Log.d("valueAvg", value_avg);
                    Log.d("value_max",value_max);

                    monitor = new Monitor(grapari.itemid,clock,num,value_min,value_avg,value_max);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return monitor;
        }

        @Override
        protected void onPostExecute(Monitor returnedGrapari) {
            progressDialog.dismiss();
            grapariCallBack.done(returnedGrapari);
            super.onPostExecute(returnedGrapari);
        }
    }
}
