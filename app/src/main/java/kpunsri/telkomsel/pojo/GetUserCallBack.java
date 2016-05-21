package kpunsri.telkomsel.pojo;

import kpunsri.telkomsel.pojo.User;

/**
 * Created by Gilbert on 2/6/2016.
 */
public interface GetUserCallBack {

    public abstract void getDataInBackground(User returnedUser);
}
