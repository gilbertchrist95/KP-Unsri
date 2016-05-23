package kpunsri.telkomsel.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kpunsri.telkomsel.pojo.GetUserCallBack;
import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.ServerUser;
import kpunsri.telkomsel.pojo.User;
import kpunsri.telkomsel.pojo.UserLocalStore;

public class FormLogin extends AppCompatActivity implements View.OnClickListener {

    private Button bSignIn;
    private EditText etEmail, etPassword;
    private TextView tvSignUp;
    private UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        bSignIn = (Button) findViewById(R.id.email_sign_in_button);
        tvSignUp = (TextView) findViewById(R.id.register_button);

        bSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    showMessageError("Email atau password kosong");
                }else{
                    User user = new User (email, password);
                    authenticate(user);
                }
                break;
            case R.id.register_button:
                register();

                break;
        }
    }

    private void register() {
        startActivity(new Intent(this, FormRegister.class));
    }

    private void authenticate(User user) {
        ServerUser serverUser = new ServerUser(this);
        serverUser.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void getDataInBackground(User returnedUser) {
                if (returnedUser == null) {
                    showMessageError("Username or password is incorrect");
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, ActivityMain.class));
    }

    private void showMessageError(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FormLogin.this);
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create().show();
    }


}
