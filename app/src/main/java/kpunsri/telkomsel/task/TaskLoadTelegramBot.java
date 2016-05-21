package kpunsri.telkomsel.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import kpunsri.telkomsel.callbacks.TelegramLoadedListener;
import kpunsri.telkomsel.extras.TelegramUtils;
import kpunsri.telkomsel.networks.VolleySingleton;
import kpunsri.telkomsel.pojo.Telegram;

/**
 * Created by Gilbert on 1/25/2016.
 */
public class TaskLoadTelegramBot extends AsyncTask<Void,Void,ArrayList<Telegram>> {

    private TelegramLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadTelegramBot(TelegramLoadedListener myComponent){
        this.myComponent = myComponent;
        volleySingleton =   VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }



    @Override
    protected ArrayList<Telegram> doInBackground(Void... params) {
        ArrayList<Telegram> listTelegram = TelegramUtils.loadTelegramBot(requestQueue);
        return listTelegram;
    }

    @Override
    protected void onPostExecute(ArrayList<Telegram> listTelegram) {
        if (myComponent != null) {
            myComponent.onTelegramBotLoaded(listTelegram);
        }
    }


}
