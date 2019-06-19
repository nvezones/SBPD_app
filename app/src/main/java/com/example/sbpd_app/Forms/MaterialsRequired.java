package com.example.sbpd_app.Forms;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sbpd_app.Database;
import com.example.sbpd_app.Home;
import com.example.sbpd_app.R;

public class MaterialsRequired extends AppCompatActivity {

    Spinner sp;
    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_required);

        sp = (Spinner) findViewById(R.id.spId_nameMaterial);
        et1 = (EditText) findViewById(R.id.etId_material33kv);
        et2 = (EditText) findViewById(R.id.etId_material11kv);
    }

    public void saveMaterials(View v) {
        String value[] = new String[3];
        value[0] = sp.getSelectedItem().toString();
        value[1] = et1.getText().toString().trim().equals("") ? "-1" : et1.getText().toString().trim();
        value[2] = et2.getText().toString().trim().equals("") ? "-1" : et2.getText().toString().trim();

        try {
            Database db = new Database(this);
            db.open();
            db.saveMaterialsReq(value);
            db.close();
            Toast.makeText(MaterialsRequired.this, "Saved successfully", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(MaterialsRequired.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            et1.setText("");
            et2.setText("");
        }

        sp.performClick();
    }

    public void goToNextMenu(View v)
    {
        startActivity(new Intent(MaterialsRequired.this,StatuOfKv.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MaterialsRequired.this,com.example.sbpd_app.Home.class));
    }
}

