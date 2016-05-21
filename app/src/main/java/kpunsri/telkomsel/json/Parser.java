package kpunsri.telkomsel.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import kpunsri.telkomsel.extras.Constant;
import kpunsri.telkomsel.extras.Keys;
import kpunsri.telkomsel.pojo.Telegram;

import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_CHAT;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_CHAT_USERNAME;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_DATE;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_FROM;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_FROM_USERNAME;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_MESSAGE;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_MESSAGE_ID;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_RESULT;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_TEXT;
import static kpunsri.telkomsel.extras.Keys.EndpointTelegram.KEY_UPDATE_ID;

/**
 * Created by Gilbert on 1/25/2016.
 */
public class Parser {
    //private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static ArrayList<Telegram> parseJSONResponse(JSONObject response) {
        ArrayList<Telegram> listTelegram = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {

                if (response.has(Keys.EndpointTelegram.KEY_RESULT)) {
                    JSONArray arrayTelegram = response.getJSONArray(KEY_RESULT);
                    for (int i = 0; i < arrayTelegram.length(); i++) {
                        long updateId = -1;
                        long messageId = -1;
                        String fromUserName = Constant.NA;
                        String chatUserName = Constant.NA;

                        String bscRncName = Constant.NA;
                        String btsName = Constant.NA;
                        String siteId = Constant.NA;
                        String cellID = Constant.NA;
                        String alarmName = Constant.NA;
                        String alarmType = Constant.NA;
                        String severity = Constant.NA;
                        Date alarmTime = null;
                        String alarmId = Constant.NA;
                        String alarmTriggeredBy = Constant.NA;
                        String alarmStatus = Constant.NA;
                        String nodeBId = Constant.NA;
                        String rncId = Constant.NA;
                        String nodeBName = Constant.NA;
                        String alarmCause = Constant.NA;

                        JSONObject currentResult = arrayTelegram.getJSONObject(i);

                        // ambil updateid
                        updateId = currentResult.getLong(KEY_UPDATE_ID);
                        // ambil message id
                        JSONObject objectMessage = currentResult.getJSONObject(KEY_MESSAGE);
                        messageId = objectMessage.getLong(KEY_MESSAGE_ID);

                        JSONObject objectFrom = objectMessage.getJSONObject(KEY_FROM);
//                            long fromId = objectFrom.getLong(KEY_FROM_ID);  //ambil from Id
                        fromUserName = objectFrom.getString(KEY_FROM_USERNAME); //ambil from username
//
                        JSONObject objectChat = objectMessage.getJSONObject(KEY_CHAT);
//                            long chatId = objectChat.getLong(KEY_CHAT_ID); //ambil chat id
                        chatUserName = objectChat.getString(KEY_CHAT_USERNAME); // ambil chat username

                        long dateUnix = objectMessage.getLong(KEY_DATE);
                        Date date = new Date(dateUnix * 1000L);
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                        String formatDate = sdf.format(date); //ambil tanggal

                        String text = objectMessage.getString(KEY_TEXT); //ambil text
                        String delimiter = "\n";
                        String temp[];

                        temp = text.split(delimiter);

                        String temp2[] = new String[20];
                        for (int j = 0; j < temp.length; j++) {
                            temp2[j] = temp[j].substring(temp[j].indexOf("[") + 1, temp[j].indexOf("]"));
                        }

                        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                        bscRncName = temp2[0];
                        btsName = temp2[1];
                        siteId = temp2[2];
                        cellID = temp2[3];
                        alarmName = temp2[4];
                        alarmType = temp2[5];
                        severity = temp2[6];
                        alarmTime = sdf.parse(temp2[7]); //date
                        alarmId = (temp2[8]);
                        alarmTriggeredBy = temp2[9];
                        alarmStatus = temp2[10];
                        nodeBId = (temp2[11]);
                        rncId = (temp2[12]);
                        nodeBName = temp2[13];
                        alarmCause = temp2[14];


                        Telegram telegram = new Telegram();

                        telegram.setUpdateID(updateId);
                        telegram.setMessageId(messageId);
//                        telegram.setFromId(fromId);
                        telegram.setFromUserName(fromUserName);
//                        telegram.setChatId(chatId);
                        telegram.setChatUserName(chatUserName);
                        telegram.setDate(formatDate);
//                        telegram.setText(text);

                        telegram.setBscRncName(bscRncName);
                        telegram.setBtsName(btsName);
                        telegram.setSiteId(siteId);
                        telegram.setCellID(cellID);
                        telegram.setAlarmName(alarmName);
                        telegram.setAlarmType(alarmType);
                        telegram.setSeverity(severity);
                        telegram.setAlarmTime(alarmTime);
                        telegram.setAlarmId(alarmId);
                        telegram.setAlarmTriggeredBy(alarmTriggeredBy);
                        telegram.setAlarmStatus(alarmStatus);
                        telegram.setNodeBId(nodeBId);
                        telegram.setRncId(rncId);
                        telegram.setNodeBName(nodeBName);
                        telegram.setAlarmCause(alarmCause);

                        listTelegram.add(telegram);

                    }
                }
            } catch (JSONException e) {

            } catch (ParseException e) {

            }
        }
        return listTelegram;
    }
}
