package com.example.gnaiz.infobubble.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gnaiz.infobubble.MainActivity;
import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.data.LogInfo;
import com.example.gnaiz.infobubble.util.HttpUtil;
import com.example.gnaiz.infobubble.util.JsonUtil;
import com.example.gnaiz.infobubble.vo.User;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="LoginActivity";
    private EditText editTextAccount;
    private EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        editTextAccount = findViewById(R.id.edtTxt_login_account);
        editTextPassword = findViewById(R.id.edtTxt_login_password);

        Button loginBtn = findViewById(R.id.btn_login_login);
        loginBtn.setOnClickListener((view)->{
            if (editTextAccount.getText().length()==0){
                Toast.makeText(LoginActivity.this, "请输入账户", Toast.LENGTH_LONG).show();
            }else if (editTextPassword.getText().length()==0){
                Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
            }else{
                this.login(editTextAccount.getText().toString(),editTextPassword.getText().toString());
            }
        });

        TextView regBtn=findViewById(R.id.btn_login_reg);
        regBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this,RegisterActivity.class);
            this.startActivity(intent);//添加this更容易理解
            this.finish();
        });
        TextView skipButton = findViewById(R.id.btn_login_skip);

        skipButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,MainActivity.class);
            this.startActivity(intent);//添加this更容易理解
            this.finish();
        });
        if(LogInfo.getInstance(LoginActivity.this).isLogin()){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(intent);
            this.finish();
        }

    }

    private void login(String username,String password){
        RequestBody requestBody=new FormBody.Builder()
                .add("user",username)
                .add("pass",password)
                .build();
        HttpUtil.sendPost("/login", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoginActivity.this.runOnUiThread(()->{
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                User user= JsonUtil.gson.fromJson(response.body().string(),User.class);
                if (user!=null){
                    Log.d(TAG, "onResponse: "+user.getUserID());
                    LoginActivity.this.runOnUiThread(()->{
                        LogInfo.getInstance(LoginActivity.this).login();
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    });

                    LogInfo.setUser(LoginActivity.this,user);
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                }else{
                    LoginActivity.this.runOnUiThread(()->{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    });
                }

            }
        });
    }
}
