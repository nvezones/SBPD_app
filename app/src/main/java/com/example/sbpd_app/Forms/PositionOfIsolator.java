package com.example.sbpd_app.Forms;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.R;

public class PositionOfIsolator extends AppCompatActivity {

    EditText et[]=new EditText[5];
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_of_isolator);

        et[0]=(EditText)findViewById(R.id.etId_isoTotal33);
        et[1]=(EditText)findViewById(R.id.etId_isoWorking33);
        et[2]=(EditText)findViewById(R.id.etId_isoDefect33);
        et[3]=(EditText)findViewById(R.id.etId_isoWorking11);
        et[4]=(EditText)findViewById(R.id.etId_isoDefect11);

        btn=(Button)findViewById(R.id.btnId_save);

        //automatically calculate defective 33kv isolator from total and working... defective=total-working
        //et[2].setEnabled(false);

    }

    public void setDefective()
    {
        int total,working;
        try {
            total = Integer.parseInt(et[0].getText().toString().trim());
            working = Integer.parseInt(et[1].getText().toString().trim());
        }catch (NumberFormatException e)
        {
            total=-1;
            working=-1;
        }
        et[2].setText(String.valueOf(total-working));
    }

    public void setSaveIso(View v)
    {
        Integer value33[]=new Integer[3];
        Integer value11[]=new Integer[3];
        String type33="33KV";
        String type11="11KV";


        value33[0] = (et[0].getText().toString().trim().equals("") ? -1 : Integer.parseInt(et[0].getText().toString().trim()));
        value33[1]= (et[1].getText().toString().trim().equals("") ? -1 : Integer.parseInt(et[1].getText().toString().trim()));
        value33[2]= (et[2].getText().toString().trim().equals("") ? -1 : Integer.parseInt(et[2].getText().toString().trim()));

        for(int i=3;i<5;i++)
            value11[i-2] = (et[i].getText().toString().trim().equals("") ? -1 : Integer.parseInt(et[i].getText().toString().trim()));

        value11[0]=value11[1]+value11[2]; //total 11kv isolator= working + defective

        try {
            Database db=new Database(this);
            db.open();
            db.saveIsolator(value33,type33); //saving in Isolator 33kv
            db.saveIsolator(value11,type11); //saving in isolator 11kv
            db.close();
            Toast.makeText(PositionOfIsolator.this,"Saved Successfully",Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e)
        {
            Toast.makeText(PositionOfIsolator.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            for(int i=0;i<5;i++)
                et[i].setText("");
        }
        startActivity(new Intent(PositionOfIsolator.this,MaterialsRequired.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PositionOfIsolator.this,com.example.sbpd_app.Home.class));
    }
}
