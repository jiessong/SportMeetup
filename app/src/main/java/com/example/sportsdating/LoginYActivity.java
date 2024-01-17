package com.example.sportsdating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sportsdating.user.UserErrorCode;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.util.TypefaceUtil;
import com.example.sportsdating.utils.StringUtil;

public class LoginYActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // SET Typeface
        TextView tv = findViewById(R.id.tx_app_name);
        TypefaceUtil.setTypeface(tv,"DELUSION.TTF");

        // chek if login successfully
        checkLogin();

        //create Listener
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.signUp).setOnClickListener(this);
    }

    /**
     * if login ---->MainActvity
     */
    private void checkLogin() {
        if (hasLogin()){
            Toast.makeText(this, "has login in to mainActivity", Toast.LENGTH_SHORT).show();
            navigateTo(MainActivity.class);
            //close loginActicity page
            finish();
        }
    }

    private boolean hasLogin() {
//        return true;
        //check if UserState status
        if(UserMgr.getInstance().isUserLoggedIn()){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login){
            //get input data from EditText
            String name = ((EditText)findViewById(R.id.username)).getText().toString();
            String password = ((EditText)findViewById(R.id.password)).getText().toString();
            //empty input
            if(StringUtil.isBlank(name) || StringUtil.isBlank(password)){
                Toast.makeText(getApplicationContext(), "Invalid Username and passowrd", Toast.LENGTH_SHORT).show();
            }else{
                UserErrorCode loginCode = UserMgr.getInstance().getCurrentUserState().login(name, password);
                if(loginCode.equals(UserErrorCode.LOGIN_SUCCESS)){
                    // open MainActivity
                    navigateTo(MainActivity.class);
                    finish();
                    Toast.makeText(this, "loginActivity To MainActivity", Toast.LENGTH_SHORT).show();
                }else{
                    //incorrect username or password
                    Toast.makeText(getApplicationContext(), loginCode.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if (view.getId() == R.id.signUp){
            // open RegisterActivityActivity
            navigateTo(RegisterActivity.class);
            Toast.makeText(this, "loginActivity To RegisterActivity", Toast.LENGTH_SHORT).show();
        }
    }

    // Jump to the specified Activity
    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}