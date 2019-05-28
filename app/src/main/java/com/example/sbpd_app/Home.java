package com.example.sbpd_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sbpd_app.Forms.Form1;
import com.example.sbpd_app.Forms.PositionBattery;

public class Home extends AppCompatActivity {

    private long backpresstime;
    Toast toastback;
    LinearLayout form1;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        form1=(LinearLayout)findViewById(R.id.form1);
        cardView=(CardView)findViewById(R.id.form2);
        form1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Form1.class));
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PositionBattery.class));
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
            toastback=Toast.makeText(getBaseContext(),"Please back again to exit",Toast.LENGTH_SHORT);
            toastback.show();
        }
        backpresstime=System.currentTimeMillis();

    }
}
