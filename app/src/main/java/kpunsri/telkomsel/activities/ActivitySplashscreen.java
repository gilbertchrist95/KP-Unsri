package kpunsri.telkomsel.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.UserLocalStore;

public class ActivitySplashscreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (authenticate() == true) {
                    startActivity(new Intent(ActivitySplashscreen.this, ActivityMain.class));
                } else {
                    startActivity(new Intent(ActivitySplashscreen.this, FormLogin.class));
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }
}