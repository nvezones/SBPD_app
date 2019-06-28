package com.example.sbpd_app.Forms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbpd_app.Home;
import com.example.sbpd_app.R;

public class LoginScreen extends AppCompatActivity {

    TextView textView;
    EditText login,password;
    SharedPreferences preferences;
    Button button;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textView=(TextView)findViewById(R.id.signuptextview);
        login=(EditText)findViewById(R.id.loginid);
        password=(EditText)findViewById(R.id.editpassword);
        button=(Button) findViewById(R.id.loginbtn);
        checkBox=(CheckBox)findViewById(R.id.checkboxrmember);
        if(checkBox.isChecked())
        {
            login.setText(preferences.getString("userid",null));
            password.setText(preferences.getString("password",null));
        }
        preferences=getSharedPreferences("loginId", Context.MODE_PRIVATE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationScreen.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l=preferences.getString("userid",null);
                String p=preferences.getString("password",null);
                if(l!=null && p!=null)
                {
                    if(login.getText().toString().equals(l) && password.getText().toString().equals(p))
                    {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"invalid user id or password",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please register first",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
