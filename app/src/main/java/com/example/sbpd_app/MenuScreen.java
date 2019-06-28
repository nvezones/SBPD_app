package com.example.sbpd_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuScreen extends AppCompatActivity {
    LinearLayout newInsp,syncOld;
    private long backpresstime;
    Toast toastback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        newInsp=(LinearLayout)findViewById(R.id.linLay_newInsp);
        syncOld=(LinearLayout)findViewById(R.id.linLay_syncOld);
        newInsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuScreen.this,com.example.sbpd_app.Forms.Form1.class));
            }
        });

        syncOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuScreen.this,SyncScreen.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(backpresstime+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            toastback.cancel();
            return;

        }
        else
        {
            toastback= Toast.makeText(getBaseContext(),"Please back again to exit",Toast.LENGTH_SHORT);
            toastback.show();
        }
        backpresstime=System.currentTimeMillis();

    }
}
