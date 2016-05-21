package kpunsri.telkomsel.callbacks;

import java.util.ArrayList;

import kpunsri.telkomsel.pojo.Telegram;

/**
 * Created by Gilbert on 1/25/2016.
 */
public interface TelegramLoadedListener {
    void onTelegramBotLoaded(ArrayList<Telegram> listTelegram);
}
