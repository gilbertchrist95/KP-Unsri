package kpunsri.telkomsel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.activities.FormResponse;
import kpunsri.telkomsel.networks.VolleySingleton;
import kpunsri.telkomsel.pojo.Telegram;

/**
 * Created by Gilbert on 1/5/2016.
 */
public class AdapterTelegram extends RecyclerView.Adapter<AdapterTelegram.ViewHolderTelegram> {

    private ArrayList<Telegram> listTelegram = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    static String itemUpdateId;
    static String itemBscRncName, itembtsName,itemSiteId, itemCellId,alarmName,alarmType,severity,alarmTime,alarmId,alarmTriggeredBy,alarmStatus;
    static String nodeBId, rncId, nodeBName, alarmCause;

    public AdapterTelegram(Context context){
        layoutInflater=LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();

    }

    public void setTelegramList(ArrayList<Telegram> listTelegram){
        this.listTelegram = listTelegram;
        notifyItemRangeChanged(0, listTelegram.size());
    }

    @Override
    public ViewHolderTelegram onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.costum_tab_1,parent,false);
        ViewHolderTelegram viewHolder = new ViewHolderTelegram(view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTelegram holder, int position) {
        Telegram curentTelegram  = listTelegram.get(position);
        holder.listTitleTelegram.setText(curentTelegram.getBtsName());
        holder.listDateTelegram.setText(curentTelegram.getAlarmTime().toString());
        holder.listUsername.setText(curentTelegram.getFromUserName());
        itemUpdateId = curentTelegram.getUpdateID().toString();

        itemBscRncName = curentTelegram.getBscRncName();
        itembtsName = curentTelegram.getBtsName();
        itemCellId = curentTelegram.getCellID();
        itemSiteId =curentTelegram.getSiteID();
        alarmName = curentTelegram.getAlarmName();
        alarmType = curentTelegram.getAlarmType();
        severity = curentTelegram.getSeverity();
        alarmTime = curentTelegram.getAlarmTime().toString();
        alarmId = curentTelegram.getAlarmId();
        alarmTriggeredBy =curentTelegram.getAlarmTriggeredBy();
        alarmStatus = curentTelegram.getAlarmStatus();

        nodeBId = curentTelegram.getNodeBId();
        rncId = curentTelegram.getRncId();
        nodeBName = curentTelegram.getNodeBName();
        alarmCause = curentTelegram.getAlarmStatus();
    }

    @Override
    public int getItemCount() {
        return listTelegram.size();
    }

    static class ViewHolderTelegram extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView listTitleTelegram;
        private TextView listDateTelegram;
        private TextView listUsername;

        public ViewHolderTelegram(View itemView) {
            super(itemView);
            listTitleTelegram = (TextView) itemView.findViewById(R.id.text_view_title);
            listDateTelegram = (TextView) itemView.findViewById(R.id.text_view_date);
            listUsername = (TextView) itemView.findViewById(R.id.text_view_username);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d("UpdateId",itemUpdateId);
                Intent intent = new Intent(v.getContext(), FormResponse.class);
                intent.putExtra(FormResponse.columnUpdateId,itemUpdateId);
                intent.putExtra(FormResponse.columnbscRncName,itemBscRncName);
                intent.putExtra(FormResponse.columnbtsName,itembtsName);
                intent.putExtra(FormResponse.columnsiteId,itemSiteId);
                intent.putExtra(FormResponse.columncellID,itemCellId);
                intent.putExtra(FormResponse.columnalarmName,alarmName);
                intent.putExtra(FormResponse.columnalarmType,alarmType);
                intent.putExtra(FormResponse.columnseverity,severity);
                intent.putExtra(FormResponse.columnAlarmTime,alarmTime);
                intent.putExtra(FormResponse.columnalarmId,alarmId);
                intent.putExtra(FormResponse.columnalarmTriggeredBy,alarmTriggeredBy);
                intent.putExtra(FormResponse.columnalarmStatus,alarmStatus);
                intent.putExtra(FormResponse.columnnodeBId,nodeBId);
                intent.putExtra(FormResponse.columnrncId,rncId);
                intent.putExtra(FormResponse.columnnodeBName,nodeBName);
                intent.putExtra(FormResponse.columnalarmCause,alarmCause);
                v.getContext().startActivity(intent);
        }
    }
}
