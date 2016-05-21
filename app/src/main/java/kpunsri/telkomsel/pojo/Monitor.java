package kpunsri.telkomsel.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Gilbert on 3/16/2016.
 */
public class Monitor {
    String itemid, clock, num, valueMin, valueAvg, value_max;
    //ini untuk grapari
    public Monitor(String itemid, String clock, String num, String valueMin, String valueAvg, String value_max){
        this.itemid = itemid;
        this.clock = clock;
        this.num = num;
        this.valueMin = valueMin;
        this.valueAvg = valueAvg;
        this.value_max = value_max;
    }
    //ini untuk mitra
    public Monitor(String itemid, String clock, String valueMin, String valueAvg, String value_max) {
        this.itemid = itemid;
        this.clock = clock;
        this.valueMin = valueMin;
        this.valueAvg = valueAvg;
        this.value_max = value_max;
    }

    public Monitor(String itemid) {
        this.itemid = itemid;
        this.clock = "";
        this.num ="";
        this.valueMin = "";
        this.valueAvg = "";
        this.value_max = "";
    }


    public String getClock() {
        Date date = new Date(Long.parseLong(clock)*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        String dateClock = simpleDateFormat.format(date);
        return dateClock;
    }

    public Double getValueMin() {
        double valueMin = Double.parseDouble(this.valueMin)/1048576;
        return (valueMin);
    }

    public Double getValueAvg() {
        double valueAvg = Double.parseDouble(this.valueAvg)/1048576;
        return (valueAvg);
    }

    public Double getValue_max() {
        double valueMax = Double.parseDouble(value_max)/1048576;
        return (valueMax);
    }
}
