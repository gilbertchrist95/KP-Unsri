package kpunsri.telkomsel.activities;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kpunsri.telkomsel.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ActivityTab3 extends Fragment {
    ActivityMain main;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.tab_3, container, false);
            return v;
    }

}