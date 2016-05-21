package kpunsri.telkomsel.activities;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.adapter.AdapterTelegram;
import kpunsri.telkomsel.callbacks.TelegramLoadedListener;
import kpunsri.telkomsel.pojo.Telegram;
import kpunsri.telkomsel.task.TaskLoadTelegramBot;
import kpunsri.telkomsel.telegram.MyTelegram;


public class FormTab1 extends Fragment implements TelegramLoadedListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_TELEGRAM = "state telegram";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ArrayList<Telegram> listTelegram = new ArrayList<>();

    private AdapterTelegram adapterTelegram;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView listItemAlarm;
    private TextView textValleyError;

    //public static final String URL_BOT_API = "https://api.telegram.org/bot";

    public FormTab1() {
        // Required empty public constructor
    }

    public static FormTab1 newInstance(String param1, String param2) {
        FormTab1 fragment = new FormTab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_TELEGRAM, listTelegram);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    private void handleVolleyError(VolleyError error) {
//        textValleyError.setVisibility(View.VISIBLE);
//        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//            textValleyError.setText(R.string.error_timeout);
//        } else if (error instanceof AuthFailureError) {
//            textValleyError.setText(R.string.error_auth_failure);
//        } else if (error instanceof ServerError) {
//            textValleyError.setText(R.string.error_auth_failure);
//        } else if (error instanceof NetworkError) {
//            textValleyError.setText(R.string.error_network);
//        } else if (error instanceof ParseError) {
//            textValleyError.setText(R.string.error_parser);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_1, container, false);
        textValleyError = (TextView) view.findViewById(R.id.textValleyError);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshTelegramBot);
        swipeRefreshLayout.setOnRefreshListener(this);
        listItemAlarm = (RecyclerView) view.findViewById(R.id.listItemAlarm);
        listItemAlarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterTelegram = new AdapterTelegram(getActivity());
        listItemAlarm.setAdapter(adapterTelegram);
        if (savedInstanceState != null) {
            listTelegram = savedInstanceState.getParcelableArrayList(STATE_TELEGRAM);
//            adapterTelegram.setTelegramList(listTelegram);
        } else {
//            sendJsonRequest();
            listTelegram = MyTelegram.getWritableDatabase().getAllTelegramBot();
            if (listTelegram.isEmpty()){
//                Toast.makeText(getActivity(), "executing task from fragment", Toast.LENGTH_SHORT).show();
                new TaskLoadTelegramBot(this).execute();
            }
        }
        adapterTelegram.setTelegramList(listTelegram);
        return view;
    }


    @Override
    public void onTelegramBotLoaded(ArrayList<Telegram> listTelegram) {
//        Toast.makeText(getActivity(), "onTelegramBotLoaded", Toast.LENGTH_SHORT).show();
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapterTelegram.setTelegramList(listTelegram);

    }

    @Override
    public void onRefresh() {
//        Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
        new TaskLoadTelegramBot(this).execute();
    }
}