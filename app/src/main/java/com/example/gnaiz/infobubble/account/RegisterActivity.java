package com.example.gnaiz.infobubble.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.util.HttpUtil;
import com.example.gnaiz.infobubble.util.JsonUtil;
import com.example.gnaiz.infobubble.util.result.CodeResult;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private LinearLayout registerLayout;
    private EditText editTextUserNum;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private String phoneNum;
    private String VCode;

    private static final String TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUserNum = this.findViewById(R.id.edtTxt_reg_username);
        registerLayout = this.findViewById(R.id.register);
        editTextPassword1 = findViewById(R.id.edtTxt_reg_password);
        editTextPassword2 = findViewById(R.id.edtTxt_reg_password_2);

        // 切换到登录页面
        Button loginBtn=this.findViewById(R.id.btn_reg_login);
        loginBtn.setOnClickListener(v->{
            Intent intent=new Intent(this,LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        });

        // 注册按钮
        Button btnRegister = (Button) findViewById(R.id.button_register_card_points);
        btnRegister.setOnClickListener(v->{
            if (editTextUserNum.getText().length() == 0) {
                Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            } else if (editTextPassword1.getText().length() == 0 || editTextPassword2.getText().length() == 0) {
                Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            } else if ( ! editTextPassword1.getText().toString().equals(editTextPassword2.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            } else {
                tryRegister(phoneNum, editTextPassword1.getText().toString());
            }
        });
    }

    // 注册
    private void tryRegister(String phoneNum,String pwd){
        RequestBody requestBody=new FormBody.Builder()
                .add("phoneNum",phoneNum)
                .add("VCode",VCode)
                .add("password",pwd)
                .build();
        HttpUtil.sendPost("/account/register", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                toast("服务器连接失败", Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CodeResult codeResult=JsonUtil.gson.fromJson(response.body().string(),CodeResult.class);
                Log.d(TAG, "onResponse: "+codeResult.getMsg());
                if (codeResult.getRstCode()==200){
                    toast(codeResult.getMsg(), Toast.LENGTH_LONG);
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void toast(String msg,int time){
        this.runOnUiThread(()->{
            Toast.makeText(this,msg,time).show();
        });
    }
}
