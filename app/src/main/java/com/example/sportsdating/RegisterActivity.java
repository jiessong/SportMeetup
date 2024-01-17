package com.example.sportsdating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;
import com.example.sportsdating.utils.StringUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btn_signUp).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signUp:
                String name = ((EditText)findViewById(R.id.register_username)).getText().toString();
                String password = ((EditText)findViewById(R.id.register_password)).getText().toString();
                if(StringUtil.isBlank(name) || StringUtil.isBlank(password)){
                    Toast.makeText(getApplicationContext(), "Invalid Username and passowrd", Toast.LENGTH_LONG).show();
                }else{
                    UserDAOImp daoImp = UserDAOImp.getInstance();
                    User user = daoImp.findUserByName(name);
                    if(user==null) {
                        daoImp.addUser(name, password);
                        UserMgr.getInstance().getCurrentUserState().login(name, password);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        navigateTo(MainActivity.class);
                    }else{
                        Toast.makeText(getApplicationContext(), "Username has already existed", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }

    // Jump to the specified Activity
    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}