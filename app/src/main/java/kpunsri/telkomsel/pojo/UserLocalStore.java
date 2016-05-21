package kpunsri.telkomsel.pojo;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by Gilbert on 2/6/2016.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User returnedUser) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", returnedUser.name);
        spEditor.putString("email", returnedUser.email);
        spEditor.putString("password", returnedUser.password);
        spEditor.putString("unit", returnedUser.unit);
        spEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
