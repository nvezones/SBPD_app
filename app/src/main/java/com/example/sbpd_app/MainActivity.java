package com.example.sbpd_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbpd_app.Forms.LoginScreen;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    private long backpresstime;
    Toast toastback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.splash_image);
        textView=(TextView)findViewById(R.id.splash_text);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splashscreen);
        Animation animation1=AnimationUtils.loadAnimation(this,R.anim.splashimage);
        imageView.setAnimation(animation);
        textView.setAnimation(animation1);
        Thread t=new Thread(){
            @Override
            public void run() {
                //super.run();
                try {
                    Thread.sleep(5000);
                }
                catch (Exception e)
                {

                }
                finally {
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                    finish();
                }
            }
        };
        t.start();
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
