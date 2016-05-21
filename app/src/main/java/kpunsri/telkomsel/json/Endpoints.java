package kpunsri.telkomsel.json;

import kpunsri.telkomsel.extras.UrlEndpoints;
import kpunsri.telkomsel.telegram.MyTelegram;

/**
 * Created by Gilbert on 1/25/2016.
 */
public class Endpoints {

    public static String getRequestURLTelegram(int limit){
        return UrlEndpoints.URL_BOT_API + MyTelegram.BOT_API_KEY_TELEGRAM + "/" + UrlEndpoints.URL_GET_UPDATES + "?limit=" + limit;
    }

}

