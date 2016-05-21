package kpunsri.telkomsel.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Gilbert on 12/28/2015.
 */
public class    Telegram implements Parcelable {

    private String ok;
    private String result;
    private long updateId;              //diambil
    private long messageId;              //diambil
    private long fromId;                //diambil
    private String fromUserName;        //diambil
    private long chatId;                //diambil
    private String chatUserName;        //diambil
    private String chatFirstName;
    private String chatLastName;
    private String chatType;
    private Date date2;
    private String date;
    private String text;                //diambil


    //diambil semua
    private String bscRncName;
    private String btsName;
    private String siteId;
    private String cellID;
    private String alarmName;
    private String alarmType;
    private String severity;
    private Date alarmTime;
    private String alarmId;
    private String alarmTriggeredBy;
    private String alarmStatus;
    private String nodeBId;
    private String rncId;
    private String nodeBName;
    private String alarmCause;


    public Telegram(){

    }

    public Telegram(Parcel input){
        updateId = input.readLong();
//        text = input.readString();
//        date = input.readString();
        messageId = input.readInt();
        fromUserName = input.readString();

        bscRncName = input.readString();
        btsName = input.readString();
        siteId = input.readString();
        cellID = input.readString();
        alarmName = input.readString();
        alarmType = input.readString();
        severity = input.readString();
        alarmTime = new Date(input.readLong());
        alarmId =  input.readString();
        alarmTriggeredBy = input.readString();
        alarmStatus =input.readString();
        nodeBId = input.readString();
        rncId = input.readString();
        nodeBName = input.readString();
        alarmCause = input.readString();

    }

    public Telegram(long updateId, String text){
        this.updateId = updateId;
        this.text = text;
    }




    public void setUpdateID(long updateId){
        this.updateId = updateId;
    }

    public Long getUpdateID(){ return  updateId;} //

    public void setMessageId(long messageId){
        this.messageId = messageId;
    }

    public Long getMessageId(){return messageId;} //

    public void setFromId(long fromId){this.fromId = fromId;}

    public void setFromUserName(String fromUserName) {this.fromUserName = fromUserName;}

    public String getFromUserName(){return fromUserName;} //

    public void setChatId(long chatId){this.chatId = chatId;}

    public void setChatUserName(String chatUserName) {this.chatUserName = chatUserName;}

    public void setDate(String date){this.date=date;}

    public String getDate(){return date;}

    public void setText(String text){
        this.text = text;
    }

    public String getText(){return text;}

    public void setBscRncName(String bscRncName){this.bscRncName = bscRncName;}

    public String getBscRncName(){return bscRncName;}

    public void setBtsName(String btsName){this.btsName = btsName;}

    public String getBtsName(){return btsName;}

    public void setSiteId(String siteId){this.siteId = siteId;}

    public String getSiteID(){return siteId;}

    public void setCellID(String cellID){this.cellID = cellID;}

    public String getCellID(){return cellID;}

    public void setAlarmName(String alarmName){this.alarmName = alarmName;}

    public String getAlarmName(){return alarmName;}

    public void setAlarmType(String alarmType){this.alarmType = alarmType;}

    public String getAlarmType(){return  alarmType;}

    public void setSeverity(String severity){this.severity = severity;}

    public String getSeverity(){return  severity;}

    public void setAlarmTime(Date alarmTime){this.alarmTime = alarmTime;}

    public Date getAlarmTime(){return alarmTime;}

    public void setAlarmId(String alarmId){this.alarmId = alarmId;}

    public String getAlarmId(){return alarmId;}

    public void setAlarmTriggeredBy(String alarmTriggeredBy){this.alarmTriggeredBy=alarmTriggeredBy;}

    public String getAlarmTriggeredBy(){return  alarmTriggeredBy;}

    public void setAlarmStatus(String alarmStatus){this.alarmStatus =alarmStatus;}

    public String getAlarmStatus(){return alarmStatus;}

    public void setNodeBId(String nodeBId){this.nodeBId = nodeBId;}

    public String getNodeBId(){return nodeBId;}

    public void setRncId(String rncId){this.rncId = rncId;}

    public String getRncId(){return rncId;}

    public void setNodeBName(String nodeBName){this.nodeBName = nodeBName;}

    public String getNodeBName(){return  nodeBName;}

    public void setAlarmCause(String alarmCause){this.alarmCause  =alarmCause;}

    public String getAlarmCause(){return alarmCause;}


    public String toString(){
//        return  bscRncName+"\n"+
//                btsName+"\n"+
//                siteId+"\n"+
//                cellID+"\n"+
//                alarmName+"\n"+
//                alarmType+"\n"+
//                severity+"\n"+
//                alarmTime+"\n"+
//                alarmId+"\n"+
//                alarmTriggeredBy+"\n"+
//                alarmStatus+"\n"+
//                nodeBId+"\n"+
//                rncId+"\n"+
//                nodeBName+"\n"+
//                alarmCause+"\n";
//        return "UpdateID: "+updateId+"\n"+bscRncName+"\n" +btsName+"\n";
////                "\nUpdateID: "+updateId+
////                "\n messageID: "+messageId+
////                "\n fromID: "+fromId+
////                "\n fromUsername: "+fromUserName+
////                "\n chatID: "+chatId+
////                "\n chatUsername: "+chatUserName+
////                "\n Text: "+text;

        return "\n";
    }


    @Override
    public int describeContents() {
        Log.d("KPunsri","describe COntent Telegram");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d("KPunsri","writeToParcel Telegram");
        dest.writeLong(updateId);
        dest.writeLong(messageId);
        dest.writeString(fromUserName);
//        dest.writeString(text);
//        dest.writeString(date);
        dest.writeString(bscRncName);
        dest.writeString(btsName);
        dest.writeString(siteId);
        dest.writeString(cellID);
        dest.writeString(alarmName);
        dest.writeString(alarmType);
        dest.writeString(severity);
        dest.writeLong(alarmTime.getTime());
        dest.writeString(alarmId);
        dest.writeString(alarmTriggeredBy);
        dest.writeString(alarmStatus);
        dest.writeString(nodeBId);
        dest.writeString(rncId);
        dest.writeString(nodeBName);
        dest.writeString(alarmCause);



    }

    public static final Parcelable.Creator<Telegram> CREATOR
            = new Parcelable.Creator<Telegram>() {
        public Telegram createFromParcel(Parcel in) {
            Log.d("KPunsri","create from Parcel :Telegram");
            return new Telegram(in);
        }

        public Telegram[] newArray(int size) {
            return new Telegram[size];
        }
    };

}
