package com.example.sbpd_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sbpd_app.PhpUtils.SyncMeter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SyncScreen extends AppCompatActivity {
    TableLayout tableLayout;
    Cursor syncTable;
    String insIdToSync="";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_screen);
        tableLayout = (TableLayout) findViewById(R.id.tblSync);
        tableLayout.removeAllViews();
        requestQueue = Volley.newRequestQueue(SyncScreen.this);
        //adding the column heading row
        TableRow heading=new TableRow(SyncScreen.this);
        heading.setLayoutParams(new TableRow.LayoutParams((TableRow.LayoutParams.MATCH_PARENT),TableRow.LayoutParams.WRAP_CONTENT));
        TextView h1=new TextView(SyncScreen.this);
        TextView h2=new TextView(SyncScreen.this);
        TextView h3=new TextView(SyncScreen.this);

        //setting properties of all the text views and adding them to table row
        h1.setTextColor(Color.BLACK);
        h1.setTextSize(18);
        h1.setPadding(10,20,10,10);
        h1.setText("INSPECTION ID");
        heading.addView(h1);

        h2.setTextColor(Color.BLACK);
        h2.setTextSize(18);
        h2.setPadding(10,20,10,10);
        h2.setText("SYNC STATUS");
        heading.addView(h2);

        h3.setTextColor(Color.BLACK);
        h3.setTextSize(18);
        h3.setPadding(10,20,10,10);
        h3.setText("SYNC TIME");
        heading.addView(h3);

        //add table row to table layout
        tableLayout.addView(heading);

        try {
            Database db = new Database(this);
            db.open();

            //pushing static data in sync_details table
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            String value[]=new String[3];
            value[0]="ins123";
            value[1]="1";
            value[2]=dateFormat.format(date);
            db.saveSyncDetails(value);
            value[0]="ins124";
            value[1]="0";
            value[2]="";
            db.saveSyncDetails(value);
            Toast.makeText(getApplicationContext(),"2 static record inserted",Toast.LENGTH_SHORT).show();
            //-----end saving static data---------
            syncTable = db.getSyncDetailsData();
            //db.close();
        } catch (SQLiteException e) {
            Log.w("sync table fetch error", e.getMessage());
        }
        int rowCount = syncTable.getCount();
        for (int i = 0; i < rowCount; i++)
        {
            syncTable.moveToPosition(i);
            int color;
            if(syncTable.getLong(1)==1)
                color=Color.GREEN;
            else
                color=Color.RED;

            TableRow tr=new TableRow(SyncScreen.this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.setBackgroundColor((getResources().getColor(R.color.orangeColor)));
            final TextView tv1=new TextView(SyncScreen.this);
            final TextView tv2=new TextView(SyncScreen.this);
            TextView tv3=new TextView(SyncScreen.this);

            //setting properties of all the text views and adding them to table row
            tv1.setTextColor(color);
            tv1.setTextSize(15);
            tv1.setPadding(10,20,10,10);
            tv1.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            tv1.setId(i+1);
            tv1.setText(syncTable.getString(0));
            tr.addView(tv1);

            tv2.setTextColor(color);
            tv2.setTextSize(15);
            tv2.setPadding(10,20,10,10);
            Long isSynced=syncTable.getLong(1);

            tv2.setText((isSynced==1?"SYNCED":"NOT SYNCED"));
            tr.addView(tv2);

            tv3.setTextColor(color);
            tv2.setTextSize(15);
            tv2.setPadding(10,20,10,10);
            tv3.setText(syncTable.getString(2));
            tr.addView(tv3);

            //add table row to table layout
            tableLayout.addView(tr);

            //tr.setClickable(true);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSync();
                    if(tv2.getText().toString().equals("NOT SYNCED")) {
                        Toast.makeText(getApplicationContext(), tv1.getText().toString(), Toast.LENGTH_SHORT).show();
                        insIdToSync = tv1.getText().toString().trim();
                        startSync();
                    }
                    else
                        Toast.makeText(getApplicationContext(),tv1.getText().toString()+" is already synced",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void startSync()
    {
        String ins[]=new String[1];
        ins[0]=insIdToSync;
        try {
            Database db=new Database(this);
            db.open();
            SyncMeter.value=db.getMeterData(ins);
            synMeterFunction();

            db.close();
        }catch (SQLiteException e)
        {
            Log.w("database fetch error",e.getMessage());
        }
    }


    public void synMeterFunction()
    {
        SyncMeter syncMeter=new SyncMeter(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);
                Toast.makeText(SyncScreen.this, "inside meter", Toast.LENGTH_LONG).show();

                try {
                    if (new JSONObject(response).getString("status").equals(("true"))) {

                        Toast.makeText(SyncScreen.this, "Meter data Synchronized", Toast.LENGTH_LONG).show();

                    } else
                        Toast.makeText(SyncScreen.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(syncMeter);

    }

}
