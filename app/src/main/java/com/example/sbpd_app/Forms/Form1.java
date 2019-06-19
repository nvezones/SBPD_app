package com.example.sbpd_app.Forms;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.sbpd_app.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Form1 extends AppCompatActivity {

    Spinner spDist,spSubDiv,spSec,spName;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
    spDist=(Spinner)findViewById(R.id.spId_dist);
    spSubDiv=(Spinner)findViewById(R.id.spId_subDiv);
    spSec=(Spinner)findViewById(R.id.spId_section);
    spName=(Spinner)findViewById(R.id.spId_pssName);
    btnSave=(Button)findViewById(R.id.btnId_save);
    }
    /*
    public void setBtnSave(View v)
    {

    }*/


}
