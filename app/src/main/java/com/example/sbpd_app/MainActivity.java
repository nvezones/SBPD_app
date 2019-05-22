package com.example.sbpd_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.splash_image);
        textView=(TextView)findViewById(R.id.splash_text);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splashscreen);
        imageView.setAnimation(animation);
        textView.setAnimation(animation);
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
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    finish();
                }
            }
        };
        t.start();
    }
}
