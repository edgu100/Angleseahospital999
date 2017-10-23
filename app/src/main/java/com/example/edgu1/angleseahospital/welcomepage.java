package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by edgu1 on 2017/10/23.
 */

public class welcomepage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(welcomepage.this,loginpage.class);
                //从启动动画ui跳转到主ui
                startActivity(intent);
                welcomepage.this.finish(); // 结束启动动画界面
            }
        }, 3000); //启动动画持续3秒钟
    }
}
