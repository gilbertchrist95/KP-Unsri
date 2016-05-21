package kpunsri.telkomsel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.activities.FormResponseMonitor;
import kpunsri.telkomsel.pojo.GetMitraCallBack;
import kpunsri.telkomsel.pojo.Monitor;
import kpunsri.telkomsel.pojo.ServerRequestMitra;

/**
 * Created by Gilbert on 2/12/2016.
 */
public class AdapterMitra extends RecyclerView.Adapter<AdapterMitra.ViewHolder> {

    private List<String> mtList;
    public Context mcontext;
    ViewHolder viewHolder;
    static long idIn, idOut;
    static DecimalFormat df = new DecimalFormat("##.##");
    static String resultCapacity,resultLastTime,resultMinIn,resultAverageIn,resultMaxIn,resultMinOut,resultAverageOut,resultMaxOut;


    public AdapterMitra(List<String> list, Context context) {
        mtList = list;
        mcontext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a layout
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_jaringan_mitra_telkomsel,null);
        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        viewHolder.hostName.setText(mtList.get(position));
    }

    @Override
    public int getItemCount() {
        return mtList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView hostName;
        public String nameMitra;

        public ViewHolder(View itemView) {
            super(itemView);
            hostName = (TextView) itemView.findViewById(R.id.nameHostName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            nameMitra = hostName.getText().toString();
            setIdInOut(nameMitra);
            setCapacity();
            setDataIn(new Monitor(Long.toString(idIn)));
            setDataOut(new Monitor(Long.toString(idOut)));
            intentToResponseMitra();
        }

        private void setCapacity() {
            resultCapacity = "2Mbps";
        }

        private void setIdInOut(String nameMitra) {
            switch (nameMitra) {
                case "Alang–AlangLebar":
                    idIn = 37662;
                    idOut = 37668;
                    break;
                case "Bandar Jaya":
                    idIn = 36996;
                    idOut = 37015;
                    break;
                case "Basuki Rahmat":
                    idIn = 33806;
                    idOut = 34250;
                    break;
                case "Baturaja":
                    idIn = 33802;
                    idOut = 34246;
                    break;
                case "Belitang":
                    idIn = 37992;
                    idOut = 37998;
                    break;
                case "Bengkulu Indah Mall":
                    idIn = 33812;
                    idOut = 34256;
                    break;
                case "Betung":
                    idIn = 33804;
                    idOut = 34248;
                    break;
                case "Curup":
                    idIn = 37566;
                    idOut = 37572;
                    break;
                case "Indralaya":
                    idIn = 37854;
                    idOut = 37860;
                    break;
                case "Jambi Inner":
                    idIn = 33814;
                    idOut = 34258;
                    break;
                case "Kalianda":
                    idIn = 34268;
                    idOut = 34274;
                    break;
                case "Kayu Agung":
                    idIn = 34292;
                    idOut = 34304;
                    break;
                case "Kedaton":
                    idIn = 37027;
                    idOut = 37032;
                    break;
                case "Kenten":
                    idIn = 37614;
                    idOut = 37620;
                    break;
                case "Kotabumi":
                    idIn = 37806;
                    idOut = 37812;
                    break;
                case "Kuala Tungkai":
                    idIn = 37630;
                    idOut = 37636;
                    break;
                case "Manna":
                    idIn = 36994;
                    idOut = 37012;
                    break;
                case "MDP":
                    idIn = 33797;
                    idOut = 34241;
                    break;
                case "Merangin":
                    idIn = 37534;
                    idOut = 37540;
                    break;
                case "Metro":
                    idIn = 37598;
                    idOut = 37604;
                    break;
                case "Muara Enim":
                    idIn = 37838;
                    idOut = 37844;
                    break;
                case "Muntok":
                    idIn = 37960;
                    idOut = 37966;
                    break;
                case "Natar":
                    idIn = 37042;
                    idOut = 37049;
                    break;
                case "Palembang Square":
                    idIn = 33808;
                    idOut = 34252;
                    break;
                case "Prabumulih":
                    idIn = 37976;
                    idOut = 37982;
                    break;
                case "Pringsewu":
                    idIn = 37678;
                    idOut = 37684;
                    break;
                case "PS":
                    idIn = 33803;
                    idOut = 34246;
                    break;
                case "Raden Inten":
                    idIn = 37646;
                    idOut = 37652;
                    break;
                case "Sarolangun":
                    idIn = 37822;
                    idOut = 37828;
                    break;
                case "Sebrang Ulu":
                    idIn = 33810;
                    idOut = 34254;
                    break;
                case "Sekayu":
                    idIn = 34294;
                    idOut = 34306;
                    break;
                case "Sribawono":
                    idIn = 37944;
                    idOut = 37950;
                    break;
                case "Sungai Liat":
                    idIn = 37582;
                    idOut = 37588;
                    break;
                case "Sungai Penuh":
                    idIn = 37928;
                    idOut = 37935;
                    break;
                case "Teluk Betung":
                    idIn = 37550;
                    idOut = 37557;
                    break;
                case "Tulang Bawang":
                    idIn = 37694;
                    idOut = 37700;
                    break;
            }
        }

        private void setDataIn(Monitor monitor) {
            ServerRequestMitra serverRequestMitra = new ServerRequestMitra(itemView.getContext());
            serverRequestMitra.fetchMitraDataInBackground(monitor, new GetMitraCallBack() {
                @Override
                public void done(Monitor monitor) {
                    if (monitor == null) {
                        showMessageError();
                    } else {
                        setResultMin(monitor);
                    }
                }
            });
        }

        private void setResultMin(Monitor monitor) {
            resultLastTime=monitor.getClock();
            resultMinIn=(df.format(monitor.getValueMin()) + " Mbps");
            resultAverageIn=(df.format(monitor.getValueAvg()) + " Mbps");
            resultMaxIn=(df.format(monitor.getValue_max()) + " Mbps");
        }

        private void setDataOut(final Monitor monitor) {
            ServerRequestMitra serverRequestMitra = new ServerRequestMitra(itemView.getContext());
            serverRequestMitra.fetchMitraDataInBackground(monitor, new GetMitraCallBack() {
                @Override
                public void done(Monitor monitor) {
                    if (monitor == null) {
                        showMessageError();
                    } else {
                        setResultOut(monitor);

                    }
                }
            });
        }

        private void setResultOut(Monitor monitor) {
            resultMinOut=df.format(monitor.getValueMin()) + " Mbps";
            resultAverageOut=(df.format(monitor.getValueAvg()) + " Mbps");
            resultMaxOut=(df.format(monitor.getValue_max()) + " Mbps");
        }

        private void intentToResponseMitra() {
            Intent i = new Intent(itemView.getContext(), FormResponseMonitor.class);
            i.putExtra("namaMonitor",nameMitra);
            i.putExtra("capacity",resultCapacity);
            i.putExtra("resultLastTime",resultLastTime);
            i.putExtra("resultMinIn",resultMinIn);
            i.putExtra("resultAverageIn",resultAverageIn);
            i.putExtra("resultMaxIn",resultMaxIn);

            i.putExtra("resultMinOut",resultMinOut);
            i.putExtra("resultAverageOut",resultAverageOut);
            i.putExtra("resultMaxOut",resultMaxOut);

            itemView.getContext().startActivity(i);
        }


        private void showMessageError() {
//            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ViewHolder.this);
//            dialogBuilder.setMessage("Data is null");
//            dialogBuilder.setPositiveButton("Ok", null);
//            dialogBuilder.show();
//            Toast.makeText(ViewHolder.this, "Data is null", Toast.LENGTH_SHORT).show();
//            Toast.makeText(ViewHolder.this, "s", Toast.LENGTH_SHORT).show();
        }
    }



}
