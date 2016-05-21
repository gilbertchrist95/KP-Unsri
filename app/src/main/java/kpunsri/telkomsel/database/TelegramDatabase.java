package kpunsri.telkomsel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import kpunsri.telkomsel.pojo.Telegram;

/**
 * Created by Gilbert on 1/21/2016.
 */
public class TelegramDatabase {
    public TelegramHelper mHelper;
    private SQLiteDatabase mDatabase;

    public TelegramDatabase(Context context){
        mHelper = new TelegramHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertTelegramBot(ArrayList<Telegram> listTelegram, boolean clearPrevious){
        if (clearPrevious){
            deleteAll();
        }

        //create a sql prepared statement
        String sql = "INSERT INTO " + TelegramHelper.TABLE_BOT_TELEGRAM + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        for (int i = 0; i < listTelegram.size(); i++) {
            Telegram currentTelegram = listTelegram.get(i);
            statement.clearBindings();
            //for a given column, index, simply bind the data to be put inside that index
            statement.bindLong(2,currentTelegram.getUpdateID());
            statement.bindLong(3,currentTelegram.getMessageId());
            statement.bindString(4,currentTelegram.getFromUserName());
            statement.bindString(5,currentTelegram.getBscRncName());
            statement.bindString(6,currentTelegram.getBtsName());
            statement.bindString(7,currentTelegram.getSiteID());
            statement.bindString(8,currentTelegram.getCellID());
            statement.bindString(9,currentTelegram.getAlarmName());
            statement.bindString(10,currentTelegram.getAlarmType());
            statement.bindString(11,currentTelegram.getSeverity());
            statement.bindLong(12, currentTelegram.getAlarmTime()== null? -1 : currentTelegram.getAlarmTime().getTime());
            statement.bindString(13,currentTelegram.getAlarmId());
            statement.bindString(14,currentTelegram.getAlarmTriggeredBy());
            statement.bindString(15,currentTelegram.getAlarmStatus());
            statement.bindString(16,currentTelegram.getNodeBId());
            statement.bindString(17,currentTelegram.getRncId());
            statement.bindString(18,currentTelegram.getNodeBName());
            statement.bindString(19,currentTelegram.getAlarmCause());
            Log.d("kpunsri","inserting entry: "+i);
            statement.execute();
        }
        //set the transaction as successfull and end the transactionn
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();

    }

    public ArrayList<Telegram> getAllTelegramBot(){
        ArrayList<Telegram> listTelegram = new ArrayList<>();

        String[] columns = {TelegramHelper.COLUMN_UID,
                TelegramHelper.COLUMN_UPDATE_ID,
                TelegramHelper.COLUMN_MESSAGE_ID,
                TelegramHelper.COLUMN_FROM_USERNAME,
                TelegramHelper.COLUMN_BSCRNCNAME,
                TelegramHelper.COLUMN_BTSNAME,
                TelegramHelper.COLUMN_SITEID,
                TelegramHelper.COLUMN_CELLID,
                TelegramHelper.COLUMN_ALARMNAME,
                TelegramHelper.COLUMN_ALARMTYPE,
                TelegramHelper.COLUMN_SEVERITY,
                TelegramHelper.COLUMN_ALARMTIME,
                TelegramHelper.COLUMN_ALARMID,
                TelegramHelper.COLUMN_ALARMTRIGGEREDBY,
                TelegramHelper.COLUMN_ALARMSTATUS,
                TelegramHelper.COLUMN_NODEBID,
                TelegramHelper.COLUMN_RNCID,
                TelegramHelper.COLUMN_NODEBNAME,
                TelegramHelper.COLUMN_ALARMCAUSE
        };
        Cursor cursor = mDatabase.query(TelegramHelper.TABLE_BOT_TELEGRAM,columns,null,null,null,null,null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                //create a new Telegram object and retrive data from the cursor to be stored in this telegram object
                Telegram telegram = new Telegram();
                //each part is 2 part of process, find the index of the column first, find the data of that using
                //thar index and finally set our blank telegram object to contain our data.
                telegram.setUpdateID(cursor.getLong(cursor.getColumnIndex(TelegramHelper.COLUMN_UPDATE_ID)));
                telegram.setMessageId(cursor.getLong(cursor.getColumnIndex(TelegramHelper.COLUMN_MESSAGE_ID)));
                telegram.setFromUserName(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_FROM_USERNAME)));
                telegram.setBscRncName(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_BSCRNCNAME)));
                telegram.setBtsName(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_BTSNAME)));
                telegram.setSiteId(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_SITEID)));
                telegram.setSiteId(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_CELLID)));
                telegram.setAlarmName(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMNAME)));
                telegram.setAlarmType(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMTYPE)));
                telegram.setSeverity(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_SEVERITY)));
                long alarmTime = (cursor.getLong(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMTIME)));
                telegram.setAlarmTime(new Date(alarmTime));
                telegram.setAlarmType(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMTYPE)));
                telegram.setAlarmId(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMID)));
                telegram.setAlarmTriggeredBy(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMTRIGGEREDBY)));
                telegram.setAlarmStatus(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMSTATUS)));
                telegram.setNodeBId(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_NODEBID)));
                telegram.setRncId(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_RNCID)));
                telegram.setNodeBName(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_NODEBNAME)));
                telegram.setAlarmCause(cursor.getString(cursor.getColumnIndex(TelegramHelper.COLUMN_ALARMCAUSE)));

                Log.d("kpunsri", "getting telegram object: "+ telegram);
                listTelegram.add(telegram);
            }
            while (cursor.moveToNext());
        }

        return listTelegram;
    }

//    public Cursor fetchTelegram(String columnUpdateId){
//
//        String[] columns = {TelegramHelper.COLUMN_UID,
//                TelegramHelper.COLUMN_UPDATE_ID,
//                TelegramHelper.COLUMN_MESSAGE_ID,
//                TelegramHelper.COLUMN_FROM_USERNAME,
//                TelegramHelper.COLUMN_BSCRNCNAME,
//                TelegramHelper.COLUMN_BTSNAME,
//                TelegramHelper.COLUMN_SITEID,
//                TelegramHelper.COLUMN_CELLID,
//                TelegramHelper.COLUMN_ALARMNAME,
//                TelegramHelper.COLUMN_ALARMTYPE,
//                TelegramHelper.COLUMN_SEVERITY,
//                TelegramHelper.COLUMN_ALARMTIME,
//                TelegramHelper.COLUMN_ALARMID,
//                TelegramHelper.COLUMN_ALARMTRIGGEREDBY,
//                TelegramHelper.COLUMN_ALARMSTATUS,
//                TelegramHelper.COLUMN_NODEBID,
//                TelegramHelper.COLUMN_RNCID,
//                TelegramHelper.COLUMN_NODEBNAME,
//                TelegramHelper.COLUMN_ALARMCAUSE
//        };
//
//        Cursor mCursor = mDatabase.query(true,TelegramHelper.TABLE_BOT_TELEGRAM,columns,TelegramHelper.COLUMN_UPDATE_ID + "=" + columnUpdateId,null,null,null,null,null);
//        if (mCursor != null){
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//    }


    public void deleteAll() {
        mDatabase.delete(TelegramHelper.TABLE_BOT_TELEGRAM, null, null);
    }

    public static class TelegramHelper extends SQLiteOpenHelper{
        private Context mContext;
        private static final String DB_NAME = "telegram_db";
        private static final int DB_VERSION = 1;
        public static final String TABLE_BOT_TELEGRAM = "telegram_table";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_UPDATE_ID = "update_Id";
        public static final String COLUMN_MESSAGE_ID = "message_Id";
        public static final String COLUMN_FROM_USERNAME = "from_Username"; //
        public static final String COLUMN_BSCRNCNAME = "bsc_rnc_name";
        public static final String COLUMN_BTSNAME = "bts_name";
        public static final String COLUMN_SITEID = "site_id";
        public static final String COLUMN_CELLID = "cell_id";
        public static final String COLUMN_ALARMNAME = "alarm_name";
        public static final String COLUMN_ALARMTYPE = "alarm_type";
        public static final String COLUMN_SEVERITY = "severity";
        public static final String COLUMN_ALARMTIME = "alarm_time";
        public static final String COLUMN_ALARMID = "alarm_id";
        public static final String COLUMN_ALARMTRIGGEREDBY = "alarm_triggered_by";
        public static final String COLUMN_ALARMSTATUS = "alarm_status";
        public static final String COLUMN_NODEBID = "node_b_id";
        public static final String COLUMN_RNCID = "rnc_id";
        public static final String COLUMN_NODEBNAME = "node_b_name";
        public static final String COLUMN_ALARMCAUSE = "alarm_cause";

        public TelegramHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                String CREATE_TABLE_TELEGRAM = "CREATE TABLE " + TABLE_BOT_TELEGRAM + " ("+
                        COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_UPDATE_ID + " INTEGER, " +
                        COLUMN_MESSAGE_ID + " INTEGER, "+
                        COLUMN_FROM_USERNAME + " TEXT, "+
                        COLUMN_BSCRNCNAME + " TEXT, " +
                        COLUMN_BTSNAME + " TEXT, " +
                        COLUMN_SITEID + " TEXT, " +
                        COLUMN_CELLID + " TEXT, " +
                        COLUMN_ALARMNAME + " TEXT, " +
                        COLUMN_ALARMTYPE + " TEXT, " +
                        COLUMN_SEVERITY + " TEXT, " +
                        COLUMN_ALARMTIME + " INTEGER, " +
                        COLUMN_ALARMID + " TEXT, " +
                        COLUMN_ALARMTRIGGEREDBY + " TEXT, " +
                        COLUMN_ALARMSTATUS + " TEXT, " +
                        COLUMN_NODEBID + " TEXT, " +
                        COLUMN_RNCID + " TEXT, " +
                        COLUMN_NODEBNAME + " TEXT, " +
                        COLUMN_ALARMCAUSE + " TEXT);";
                db.execSQL(CREATE_TABLE_TELEGRAM);
                Log.d("TAG","create table telegram");
            }
            catch (SQLiteException exception){
                Toast.makeText(mContext, exception + " ", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Log.d("TAG","updgrade table telegram executed");
                db.execSQL("DROP TABLE " + TABLE_BOT_TELEGRAM + " IF EXISTS;");
                onCreate(db);
            }catch (SQLiteException exception){
                Toast.makeText(mContext, exception +"", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
