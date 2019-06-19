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

public class StatuOfKv extends AppCompatActivity {

    EditText et[]=new EditText[9];
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statu_of_kv);

        et[0]=(EditText)findViewById(R.id.etId_LA33total);
        et[1]=(EditText)findViewById(R.id.etId_LA33working);
        et[2]=(EditText)findViewById(R.id.etId_LA33defect);
        et[3]=(EditText)findViewById(R.id.etId_LA33req);

        et[4]=(EditText)findViewById(R.id.etId_LA11total);
        et[5]=(EditText)findViewById(R.id.etId_LA11working);
        et[6]=(EditText)findViewById(R.id.etId_LA11defect);
        et[7]=(EditText)findViewById(R.id.etId_LA11req);

        et[8]=(EditText)findViewById(R.id.etId_LAremarks);
    }

    public void setSaveLA(View v)
    {
        Integer value33[]=new Integer[4];
        Integer value11[]=new Integer[4];
        String rem="";
        rem=et[8].getText().toString().trim();

        for(int i=0;i<4;i++)
        {
            try{
                value33[i]=Integer.parseInt(et[i].getText().toString().trim());
            }catch (NumberFormatException e)
            {
                value33[i]=-1;
            }
        }

        for(int i=4;i<8;i++)
        {
            try{
                value11[i-4]=Integer.parseInt(et[i].getText().toString().trim());
            }catch (NumberFormatException e)
            {
                value11[i-4]=-1;
            }
        }
        //check integrity: working+defective<=total
        try{
            Database db=new Database(this);
            db.open();
            db.saveLA(value33,"33KV",rem);
            db.saveLA(value11,"11KV",rem);
            db.close();
            Toast.makeText(StatuOfKv.this,"Saved successfully",Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e)
        {
            Toast.makeText(StatuOfKv.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            for(int i=0;i<9;i++)
                et[i].setText("");
        }

        startActivity(new Intent(StatuOfKv.this,StatusOfMetering.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StatuOfKv.this,com.example.sbpd_app.Home.class));
    }
}
