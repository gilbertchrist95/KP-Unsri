package kpunsri.telkomsel.activities;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.task.DisplayMonitor;

/**
 * Created by hp1 on 21-01-2015.
 */
public class FormTab2 extends Fragment {

    private Button grapari, mitra;
    protected DisplayMonitor dMonitor;
    private int request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_2, container, false);

        grapari = (Button) view.findViewById(R.id.button_jaringan_grapari);
        mitra = (Button) view.findViewById(R.id.button_jaringan_mitra);
        dMonitor = new DisplayMonitor(getActivity());

        grapari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(),FormGrapari.class));
                request = 1;
                setAndLoad();
            }
        });

        mitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), FormMitra.class));
                request = 2;
                setAndLoad();
            }
        });
        return view;
    }

    private void setAndLoad() {
        dMonitor.setRequestForm(request);
        dMonitor.loadFormMonitor();

    }


}