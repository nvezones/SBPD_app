package com.example.sbpd_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private long backpresstime;
    Toast toastback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
