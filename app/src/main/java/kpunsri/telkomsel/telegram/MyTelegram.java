package kpunsri.telkomsel.telegram;

import android.app.Application;
import android.content.Context;

import kpunsri.telkomsel.database.TelegramDatabase;

/**
 * Created by Gilbert on 12/27/2015.
 */
public class MyTelegram extends Application {

    public static final String BOT_API_KEY_TELEGRAM = "179819727:AAFb4rhR19e3tOulQ0J_Uj1O911elBa0Xo8";
    public static final String sendMessage = "/sendMessage?";
    private static MyTelegram sInstance;

    private static TelegramDatabase mDatabase;

    public static MyTelegram getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public synchronized static TelegramDatabase getWritableDatabase(){
        if (mDatabase == null){
            mDatabase = new TelegramDatabase(getAppContext());
        }
        return mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase = new TelegramDatabase(this);
    }
}
