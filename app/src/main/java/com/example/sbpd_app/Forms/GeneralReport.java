package com.example.sbpd_app.Forms;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.RadialGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.R;

public class GeneralReport extends AppCompatActivity {

    RadioGroup rg[] =new RadioGroup[21];
    RadioButton rb[]=new RadioButton[42];
    EditText etmanPower;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_report);
        rg[0]=(RadioGroup)findViewById(R.id.rgId_lighting);
        rg[1]=(RadioGroup)findViewById(R.id.rgId_ltCtrl);

        //status of lighting in switching room
        rb[0]=(RadioButton)findViewById(R.id.rgId_lightingAvl);
        rb[1]=(RadioButton)findViewById(R.id.rgId_lightingNvl);

        //status of lighting in control room
        rb[2]=(RadioButton)findViewById(R.id.rgId_ltCtrlAvl);
        rb[3]=(RadioButton)findViewById(R.id.rgId_ltCtrlNvl);

        //status of gents toilet
        rb[4]=(RadioButton)findViewById(R.id.rgId_ToiletGAvl);
        rb[5]=(RadioButton)findViewById(R.id.rgId_ToiletGNvl);

        //status of ladies toilet
        rb[6]=(RadioButton)findViewById(R.id.rgId_ToiletLAvl);
        rb[7]=(RadioButton)findViewById(R.id.rgId_ToiletLNvl);

        //furniture in control room
        rb[8]=(RadioButton)findViewById(R.id.rgId_FurnAvl);
        rb[9]=(RadioButton)findViewById(R.id.rgId_FurnNvl);

        //fan in control room
        rb[10]=(RadioButton)findViewById(R.id.rgId_FanAvl);
        rb[11]=(RadioButton)findViewById(R.id.rgId_FanNvl);

        //grass cutting in pss campus
        rb[12]=(RadioButton)findViewById(R.id.rgId_GrassDone);
        rb[13]=(RadioButton)findViewById(R.id.rgId_GrassNDone);

        //tap water
        rb[14]=(RadioButton)findViewById(R.id.rgId_TapAvl);
        rb[15]=(RadioButton)findViewById(R.id.rgId_TapNvl);

        //RO water
        rb[16]=(RadioButton)findViewById(R.id.rgId_ROAvl);
        rb[17]=(RadioButton)findViewById(R.id.rgId_RONvl);

        //status of tools and plants
        rb[18]=(RadioButton)findViewById(R.id.rgId_ToolsAvl);
        rb[19]=(RadioButton)findViewById(R.id.rgId_ToolsNvl);

        //hand gloves
        rb[20]=(RadioButton)findViewById(R.id.rgId_GlovesAvl);
        rb[21]=(RadioButton)findViewById(R.id.rgId_GlovesNvl);

        //gum boot
        rb[22]=(RadioButton)findViewById(R.id.rgId_GumAvl);
        rb[23]=(RadioButton)findViewById(R.id.rgId_GumNvl);

        //rain coat
        rb[24]=(RadioButton)findViewById(R.id.rgId_RainAvl);
        rb[25]=(RadioButton)findViewById(R.id.rgId_RainNvl);

        //Fire extinguisher
        rb[26]=(RadioButton)findViewById(R.id.rgId_FireAvl);
        rb[27]=(RadioButton)findViewById(R.id.rgId_FireNvl);

        //Status of discharge rod
        rb[28]=(RadioButton)findViewById(R.id.rgId_DischargeAvl);
        rb[29]=(RadioButton)findViewById(R.id.rgId_DischargeNvl);

        //status of first aid box
        rb[30]=(RadioButton)findViewById(R.id.rgId_FirstaidAvl);
        rb[31]=(RadioButton)findViewById(R.id.rgId_FirstaidNvl);

        //status of roof
        rb[32]=(RadioButton)findViewById(R.id.rgId_roofLeak);
        rb[33]=(RadioButton)findViewById(R.id.rgId_roofNLeak);

        //condition of boundary wall
        rb[34]=(RadioButton)findViewById(R.id.rgId_wallOk);
        rb[35]=(RadioButton)findViewById(R.id.rgId_wallNOk);

        //condition of gate of pss
        rb[36]=(RadioButton)findViewById(R.id.rgId_gateOk);
        rb[37]=(RadioButton)findViewById(R.id.rgId_gateNOk);

        //No. of manpower
        etmanPower=(EditText)findViewById(R.id.etId_manpower);

        //status of different register
        rb[38]=(RadioButton)findViewById(R.id.rgId_registerAvl);
        rb[39]=(RadioButton)findViewById(R.id.rgId_registerNvl);

        //Entry of PSS Data in Companies Prescribed Log Book
        rb[40]=(RadioButton)findViewById(R.id.rgId_LogYes);
        rb[41]=(RadioButton)findViewById(R.id.rgId_LogNo);

        //by default negative options will be selected
        for(int i=0;i<42;i++)
        {
            if(i%2!=0) {
                rb[i].setChecked(true);
                Toast.makeText(GeneralReport.this,i+" "+rb[39].getText(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setSaveGenRep(View v)
    {
        final Integer value[] =new Integer[22];
        for(int k=0;k<22;k++)
            value[k]=-1;
        int j=0,i=0;
        for(i=0;i<38;i++)
        {
            if(rb[i].isChecked()) {
                if (i%2 == 0)
                    {value[j] = 1; j++; }
                else
                    {value[j] = 0; j++;}
            }
            // j=19 value[19] must be manpower hence used break
            if(i==37)   break;
        }

        Integer manPower;
        String mp=etmanPower.getText().toString().trim();
        if(mp.equals("")) {
            Toast.makeText(GeneralReport.this, "No. of Manpower field is empty", Toast.LENGTH_SHORT).show();
            manPower=-1;
        }
        else
            manPower = new Integer(mp);
        value[j]=manPower;
        j++;        //j=20

        for(i=38;i<42;i++)
        {
            if(rb[i].isChecked()) {
                if (i%2 == 0)
                    {value[j] = 1; j++; }
                else
                    {value[j] = 0; j++;}
            }
        }

        try{
            Database db=new Database(this);
            db.open();
            db.saveGeneralReport(value);
            db.close();
            Toast.makeText(GeneralReport.this,"Saved successfully",Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e)
        {
            Toast.makeText(GeneralReport.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(GeneralReport.this,com.example.sbpd_app.MenuScreen.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GeneralReport.this,com.example.sbpd_app.Home.class));
    }
}
