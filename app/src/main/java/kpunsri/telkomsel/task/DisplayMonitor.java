package kpunsri.telkomsel.task;

import android.content.Context;
import android.content.Intent;

import kpunsri.telkomsel.activities.FormGrapari;
import kpunsri.telkomsel.activities.FormMitra;

/**
 * Created by Gilbert on 3/31/2016.
 */
public class DisplayMonitor{

    private Context context;
    private int requestForm;

    public DisplayMonitor(Context context){
        this.context = context;
    }


    public void setRequestForm(int requestForm){
        this.requestForm = requestForm;
    }

    public void loadFormMonitor(){
        if (requestForm == 1){
            Intent intent = new Intent(context,FormGrapari.class);
            context.startActivity(intent);
        }
        else if (requestForm ==2){
            Intent intent = new Intent(context,FormMitra.class);
            context.startActivity(intent);
        }

    }
}
