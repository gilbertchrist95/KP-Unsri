package kpunsri.telkomsel.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import kpunsri.telkomsel.json.Endpoints;
import kpunsri.telkomsel.json.Parser;
import kpunsri.telkomsel.json.Requestor;
import kpunsri.telkomsel.pojo.Telegram;
import kpunsri.telkomsel.telegram.MyTelegram;

/**
 * Created by Gilbert on 1/25/2016.
 */
public class TelegramUtils {
    public static ArrayList<Telegram> loadTelegramBot(RequestQueue requestQueue){
        JSONObject response = Requestor.requestTelegramBot(requestQueue, Endpoints.getRequestURLTelegram(30));
        ArrayList<Telegram> listTelegram = Parser.parseJSONResponse(response);
        MyTelegram.getWritableDatabase().insertTelegramBot(listTelegram, true);
        return listTelegram;
    }
}
