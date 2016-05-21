package kpunsri.telkomsel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kpunsri.telkomsel.pojo.GetUserCallBack;
import kpunsri.telkomsel.R;
import kpunsri.telkomsel.pojo.ServerUser;
import kpunsri.telkomsel.pojo.User;

public class FormRegister extends AppCompatActivity implements View.OnClickListener{

    private Button bSignUp;
    private EditText etEmail, etName, etPassword, etUnit;
    private User user;
    private ServerUser serverUser;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);

        etName = (EditText)findViewById(R.id.name);
        etEmail = (EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);
        etUnit = (EditText)findViewById(R.id.unit);
        bSignUp = (Button)findViewById(R.id.email_sign_up_button);

        bSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_sign_up_button:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String unit = etUnit.getText().toString();
                user  =  new User (name, email,password, unit);
                registerUser(user);
                break;
        }
    }

    private void registerUser(User user) {
        serverUser = new ServerUser(this);
        serverUser.storeUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void getDataInBackground(User returnedUser) {
                startActivity(new Intent(FormRegister.this,FormLogin.class));
            }
        });
    }
}
