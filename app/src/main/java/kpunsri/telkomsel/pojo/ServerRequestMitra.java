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
 * Created by Gilbert on 2/18/2016.
 */
public class ServerRequestMitra {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIME_Mitra = 10000 * 15;
    public static final String SERVER_ADDRESS = "http://alarmbot.comlu.com/";

    public ServerRequestMitra(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait");
    }

    public void fetchMitraDataInBackground(Monitor mitra, GetMitraCallBack callBack) {
        progressDialog.show();
        new fetchMitraDataAsyncTask(mitra,callBack).execute();

    }

    public class fetchMitraDataAsyncTask extends AsyncTask<Void, Void, Monitor> {

        Monitor mitra;
        GetMitraCallBack mitraCallBack;

        public fetchMitraDataAsyncTask(Monitor mitra, GetMitraCallBack mitraCallBack) {
            this.mitra = mitra;
            this.mitraCallBack = mitraCallBack;
        }

        @Override
        protected Monitor doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("itemid", mitra.itemid));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIME_Mitra);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIME_Mitra);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchMitraData.php");

            Monitor returnedMitra = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                String result = EntityUtils.toString(httpEntity);
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.length() == 0) {
                    returnedMitra = null;
                } else {
//                    String itemId = jsonObject.getString("itemId");
                    String clock = jsonObject.getString("clock");
                    String value_min = jsonObject.getString("value_min");
                    String value_avg = jsonObject.getString("value_avg");
                    String value_max = jsonObject.getString("value_max");

                    Log.d("clock",clock);
                    Log.d("valueMin", value_min);
                    Log.d("valueAvg", value_avg);
                    Log.d("value_max",value_max);

                    returnedMitra = new Monitor(mitra.itemid,clock,value_min, value_avg, value_max);
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

            return returnedMitra;
        }

        @Override
        protected void onPostExecute(Monitor returnedMitra) {
            progressDialog.dismiss();
            mitraCallBack.done(returnedMitra);
            super.onPostExecute(returnedMitra);
        }
    }

}
