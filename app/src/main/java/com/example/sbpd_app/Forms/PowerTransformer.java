package com.example.sbpd_app.Forms;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.R;
import com.example.sbpd_app.SyncScreen;

import org.w3c.dom.Text;

public class PowerTransformer extends AppCompatActivity {

    Spinner sp;
    EditText et[] = new EditText[11];
    RadioButton rb[] = new RadioButton[12];
    Button btnSave,btn1;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_transformer);
        sp = (Spinner) findViewById(R.id.spId_PT);
        et[0] = (EditText) findViewById(R.id.etId_slno);
        et[1] = (EditText) findViewById(R.id.etId_mfgYear);
        et[2] = (EditText) findViewById(R.id.etId_make);
        et[3] = (EditText) findViewById(R.id.etId_oilLevel);
        et[4] = (EditText) findViewById(R.id.etId_BDVTop);
        et[5] = (EditText) findViewById(R.id.etId_BDVBottom);

        rb[0] = (RadioButton) findViewById(R.id.rgId_buRelayWorking);
        rb[1] = (RadioButton) findViewById(R.id.rgId_buRelayNotWorking);

        et[6] = (EditText) findViewById(R.id.etId_oilLeakage);

        rb[2] = (RadioButton) findViewById(R.id.rgId_tempWindingWorking);
        rb[3] = (RadioButton) findViewById(R.id.rgId_tempWindingNotWorking);
        rb[4] = (RadioButton) findViewById(R.id.rgId_tempOilWorking);
        rb[5] = (RadioButton) findViewById(R.id.rgId_tempOilNotWorking);
        rb[6] = (RadioButton) findViewById(R.id.rgId_silicaGelOk);
        rb[7] = (RadioButton) findViewById(R.id.rgId_silicaGelNotOk);

        et[7] = (EditText) findViewById(R.id.etId_resistanceNeutral);
        et[8] = (EditText) findViewById(R.id.etId_resistanceBody);

        rb[8] = (RadioButton) findViewById(R.id.rgId_htLtOk);
        rb[9] = (RadioButton) findViewById(R.id.rgId_htLtNotOk);
        rb[10] = (RadioButton) findViewById(R.id.rgId_bushConnectOk);
        rb[11] = (RadioButton) findViewById(R.id.rgId_bushConnectNotOk);

        et[9] = (EditText) findViewById(R.id.etId_plinthPos);
        et[10] = (EditText) findViewById(R.id.etId_earthPitPos);

        btnSave = (Button) findViewById(R.id.btnId_update);
        scrollView=(ScrollView)findViewById(R.id.scrollPT);
        //by default negative options will be selected
        for (int i = 0; i < 12; i++)
            if (i % 2 != 0) rb[i].setChecked(true);
    }

    public void setSavePT(View v) {
        String value[] = new String[18];
        value[0] = sp.getSelectedItem().toString();
        for (int i = 0; i < 6; i++)
            value[i + 1] = (et[i].getText().toString().trim().equals("") ? "-1" : et[i].getText().toString().trim());

        value[7] = (rb[0].isChecked() ? "1" : "0");
        value[8] = (et[6].getText().toString().trim().equals("") ? "-1" : et[6].getText().toString().trim());

        int j = 9;
        for (int i = 2; i < 8; i++) {
            if (rb[i].isChecked())
                value[j++] = (i % 2 == 0 ? "1" : "0");
        }
        //Toast.makeText(PowerTransformer.this, j+" ", Toast.LENGTH_SHORT).show();
        value[j++] = (et[7].getText().toString().trim().equals("") ? "-1" : et[7].getText().toString().trim());
        value[j++] = (et[8].getText().toString().trim().equals("") ? "-1" : et[8].getText().toString().trim());
        //Toast.makeText(PowerTransformer.this, j+" ", Toast.LENGTH_SHORT).show();
        for (int i = 8; i < 12; i++) {
            if (rb[i].isChecked())
                value[j++] = (i % 2 == 0 ? "1" : "0");
        }
        //Toast.makeText(PowerTransformer.this, j+" ", Toast.LENGTH_SHORT).show();
        value[j++] = (et[9].getText().toString().trim().equals("") ? "-1" : et[9].getText().toString().trim());
        value[j++] = (et[10].getText().toString().trim().equals("") ? "-1" : et[10].getText().toString().trim());
        //Toast.makeText(PowerTransformer.this, j+" ", Toast.LENGTH_SHORT).show();
        try {
            Database db = new Database(this);
            db.open();
            db.savePowerTransformer(value);
            db.close();
            Toast.makeText(PowerTransformer.this, "Saved successfully", Toast.LENGTH_SHORT).show();

            //clearing text fields
            for(int i=0;i<11;i++)
                et[i].setText("");

            //setting radio buttons to default
            for (int i = 0; i < 12; i++)
                if (i % 2 != 0) rb[i].setChecked(true);

            //scrolling to top
            //if all PTs in the spinner is saved then don't scroll.
            //currently there are 5 items. so when last item is selected sp.getSelectedItemPosition() returns 4
            int pos=sp.getSelectedItemPosition();
            if(pos<=4)
                scrollToPT();
            else // go to next menu
                startActivity(new Intent(PowerTransformer.this,VCB33KV.class));
        } catch (SQLiteException e) {
            Toast.makeText(PowerTransformer.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void scrollToPT()
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                int pos=sp.getSelectedItemPosition() + 1;
                sp.setSelection(pos);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PowerTransformer.this,com.example.sbpd_app.Home.class));
    }
}
