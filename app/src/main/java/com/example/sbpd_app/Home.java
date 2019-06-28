package com.example.sbpd_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sbpd_app.Forms.Form1;
import com.example.sbpd_app.Forms.GeneralReport;
import com.example.sbpd_app.Forms.MaterialsRequired;
import com.example.sbpd_app.Forms.PhotoAttactment;
import com.example.sbpd_app.Forms.PositionBattery;
import com.example.sbpd_app.Forms.PositionOfIsolator;
import com.example.sbpd_app.Forms.PowerTransformer;
import com.example.sbpd_app.Forms.StatuOfKv;
import com.example.sbpd_app.Forms.StatusOfMeter;
import com.example.sbpd_app.Forms.StatusOfMetering;
import com.example.sbpd_app.Forms.VCB11KV;
import com.example.sbpd_app.Forms.VCB33KV;

public class Home extends AppCompatActivity {

    LinearLayout form1,isofrm,statuskvla,statuskvmeter,powertrans,material,vcb33,vcb11,genReport,meter,photoattachment;
    CardView cardView,cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        form1=(LinearLayout)findViewById(R.id.form1);
        isofrm=(LinearLayout)findViewById(R.id.frmiso);
        statuskvmeter=(LinearLayout)findViewById(R.id.statusmetering);
        statuskvla=(LinearLayout)findViewById(R.id.status11kv33kv);
        cardView=(CardView)findViewById(R.id.form2);
        powertrans=(LinearLayout)findViewById(R.id.linLayId_powerTrans);
        material=(LinearLayout)findViewById(R.id.linLayId_material);
        vcb33=(LinearLayout)findViewById(R.id.linLayId_vcb33kv);
        vcb11=(LinearLayout)findViewById(R.id.linLayId_vcb11kv);
        genReport=(LinearLayout)findViewById(R.id.linLayId_genReport);
        meter=(LinearLayout)findViewById(R.id.linLayId_Meter);
        photoattachment=(LinearLayout)findViewById(R.id.photoattachment);
        photoattachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhotoAttactment.class));
            }
        });
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
        isofrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PositionOfIsolator.class));
            }
        });
        statuskvla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StatuOfKv.class));
            }
        });
        statuskvmeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StatusOfMetering.class));
            }
        });

        powertrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PowerTransformer.class));
            }
        });

        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MaterialsRequired.class));
            }
        });

        vcb33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VCB33KV.class));
            }
        });

        vcb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VCB11KV.class));
            }
        });

        genReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GeneralReport.class));
            }
        });

        meter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StatusOfMeter.class));
            }
        });
    }


}
