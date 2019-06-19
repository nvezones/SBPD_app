package com.example.sbpd_app.Forms;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PositionBattery extends AppCompatActivity {

    RadioButton rb[]=new RadioButton[4];
    EditText et[]=new EditText[5];
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_battery);

        //Water Level
        rb[0]=(RadioButton)findViewById(R.id.rgId_waterFull);
        rb[1]=(RadioButton)findViewById(R.id.rgId_waterNFull);

        //Terminal
        rb[2]=(RadioButton)findViewById(R.id.rgId_termOk);
        rb[3]=(RadioButton)findViewById(R.id.termNOk);

        //Voltage
        et[0]=(EditText)findViewById(R.id.etId_voltage);
        //Gravity
        et[1]=(EditText)findViewById(R.id.etId_Gravity);
        //supply Volatage AC
        et[2]=(EditText)findViewById(R.id.etId_voltAC);
        //supply voltage DC
        et[3]=(EditText)findViewById(R.id.etId_voltDC);
        //Charging Current
        et[4]=(EditText)findViewById(R.id.etId_chargeCurrent);

        //by default negative options will be selected
        for(int i=0;i<4;i++)
            if(i%2!=0) rb[i].setChecked(true);
        btnSave=(Button)findViewById(R.id.btnId_save);
    }

    public void setSavePosBatt(View v)
    {
        String valueBattery[]=new String[4];
        Integer valueBattCharger[]=new Integer[3];

        valueBattery[0]=(rb[0].isChecked()==true?"1":"0");
        valueBattery[1]=(rb[2].isChecked()==true?"1":"0");

        String volt=et[0].getText().toString().trim();
        String gravity=et[1].getText().toString().trim();
        valueBattery[2]=(volt.equals("")?"-1":volt);
        valueBattery[3]=(gravity.equals("")?"-1":gravity);

       for(int i=2;i<=4;i++)
       {
           if(et[i].getText().toString().trim().equals(""))
               valueBattCharger[i-2]=-1;
           else
               valueBattCharger[i-2]=Integer.parseInt(et[i].getText().toString().trim());
       }
        //insertig valueBattery in Battery table
       try {
            Database db=new Database(this);
            db.open();
            db.savePositionBattery(valueBattery);
            db.close();
            Toast.makeText(PositionBattery.this,"Saved successfully",Toast.LENGTH_SHORT).show();
       }catch (SQLiteException e) {
             Toast.makeText(PositionBattery.this,e.getMessage(),Toast.LENGTH_SHORT).show();
       }finally {
           for(int i=0;i<2;i++)
               et[i].setText("");
       }


       //inserting valueBattCharger in Battery charger table
        try {
            String table="BatteryCharger";
            Database db=new Database(this);
            db.open();
            db.savePositionBatteryCharger(valueBattCharger);
            db.close();
            Toast.makeText(PositionBattery.this,"Saved successfully",Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e) {
            Toast.makeText(PositionBattery.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            for(int i=2;i<5;i++)
                et[i].setText("");
        }
        //after saving go to next page
        startActivity(new Intent(getApplicationContext(),PowerTransformer.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PositionBattery.this,com.example.sbpd_app.Home.class));
    }
}
