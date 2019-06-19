package com.example.sbpd_app.Forms;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.R;

public class VCB11KV extends AppCompatActivity {

    EditText et[]=new EditText[4];
    RadioButton rb[]=new RadioButton[18];
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcb11_kv);
        et[0]=(EditText)findViewById(R.id.etId_nameVcb33kv);

        rb[0]=(RadioButton)findViewById(R.id.rgId_posCTWorking);
        rb[1]=(RadioButton)findViewById(R.id.rgId_posCNotTWorking);
        rb[2]=(RadioButton)findViewById(R.id.rgId_intrupterWorking);
        rb[3]=(RadioButton)findViewById(R.id.rgId_intrupterNotTWorking);
        rb[4]=(RadioButton)findViewById(R.id.rgId_mechanismWorking);
        rb[5]=(RadioButton)findViewById(R.id.rgId_mechanismNotTWorking);
        rb[6]=(RadioButton)findViewById(R.id.rgId_motorWorking);
        rb[7]=(RadioButton)findViewById(R.id.rgId_motorNotTWorking);
        rb[8]=(RadioButton)findViewById(R.id.rgId_tripCoilWorking);
        rb[9]=(RadioButton)findViewById(R.id.rgId_tripCoilNotTWorking);
        rb[10]=(RadioButton)findViewById(R.id.rgId_closeCoilWorking);
        rb[11]=(RadioButton)findViewById(R.id.rgId_closeCoilNotTWorking);
        rb[12]=(RadioButton)findViewById(R.id.rgId_earthPanelWorking);
        rb[13]=(RadioButton)findViewById(R.id.rgId_earthPanelNotTWorking);
        rb[14]=(RadioButton)findViewById(R.id.rgId_relayWorking);
        rb[15]=(RadioButton)findViewById(R.id.rgId_relayNotTWorking);
        rb[16]=(RadioButton)findViewById(R.id.rgId_masterRelayWorking);
        rb[17]=(RadioButton)findViewById(R.id.rgId_masterRelayNotTWorking);

        et[1]=(EditText)findViewById(R.id.etId_slno);
        et[2]=(EditText)findViewById(R.id.etId_mfgYear);
        et[3]=(EditText)findViewById(R.id.etId_make);

        btnUpdate=(Button)findViewById(R.id.btnId_update);

        //by default negative options will be selected
        for(int i=0;i<18;i++)
            rb[i].setChecked(true);
        for(int i=0;i<4;i++)
            et[i].setText("");
    }

    public void setSaveVCB11(View v)
    {
        String value[]=new String[13];
        String table="VCB";
        String type="11KV"; //typeofVCB=33KV
        value[0]=(et[0].getText().toString().trim().equals("")?"-1":et[0].getText().toString().trim());
        int j=1;
        for(int i=0;i<18;i++)
        {
            if(rb[i].isChecked())
            {
                value[j++]=(i%2==0)?"1":"0";
            }
        }
        value[j++]=(et[1].getText().toString().trim().equals("")?"-1":et[1].getText().toString().trim());
        value[j++]=(et[2].getText().toString().trim().equals("")?"-1":et[2].getText().toString().trim());
        value[j]= et[3].getText().toString().trim().equals("")?"-1":et[3].getText().toString().trim();

        try{
            Database db=new Database(this);
            db.open();
            db.saveVCB(value,type);
            db.close();
            Toast.makeText(VCB11KV.this,"Saved Successfully",Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e)
        {
            Toast.makeText(VCB11KV.this,"Saved Successfully",Toast.LENGTH_SHORT).show();
        }
        finally {
            for(int i=0;i<4;i++)
                et[i].setText("");
        }
        startActivity(new Intent(VCB11KV.this,PositionOfIsolator.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VCB11KV.this,com.example.sbpd_app.Home.class));
    }
}

