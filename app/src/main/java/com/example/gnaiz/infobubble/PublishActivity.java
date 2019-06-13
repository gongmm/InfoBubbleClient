package com.example.gnaiz.infobubble;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gnaiz.infobubble.util.TitleBar;

public class PublishActivity extends AppCompatActivity {

    private EditText comment_content;
    private Button button;
    private Context context = PublishActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        toolBar();
        comment_content = findViewById(R.id.editText_comment);
        button = findViewById(R.id.button_comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comment_content.getText().length()==0)
                    showAlert("发布内容不能为空");
                else {
                    //strAccount = editTextAccount.getText().toString();
                    //                    //strPassword = editTextPassword.getText().toString();
                    //                    //
                    //                    //tryLogin();
                    finish();
                }
            }
        });
    }

    private void showAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }

    public void toolBar(){

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setHeight(160);
        // left
        titleBar.setLeftImageResource(R.drawable.ic_back_24dp);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorPrimaryDark));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            // 跳转回主页
            @Override
            public void onClick(View v) {

                Intent intentToUser = new Intent(context, UserActivity.class);
                startActivity(intentToUser);
                finish();
            }
        });

        titleBar.setTitle("发布");
        titleBar.setTitleSize(25);
        titleBar.setTitleColor(getResources().getColor(R.color.colorPrimaryDark));

    }
}
