package com.example.sbpd_app.Forms;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sbpd_app.Home;
import com.example.sbpd_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText id,phone,password;
    Button button;
    FirebaseAuthSettings firebaseAuthSettings;
    Dialog dialog;
    Button diabtn,cancelbtn;
    EditText otpcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        id=(EditText)findViewById(R.id.userid);
        phone=(EditText)findViewById(R.id.phoneuser);
        password=(EditText)findViewById(R.id.passworduser);
        button=(Button)findViewById(R.id.signupbtn);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuthSettings=firebaseAuth.getFirebaseAuthSettings();
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogboxcustom);
        otpcode=(EditText)dialog.findViewById(R.id.otpcodetext);
        diabtn=(Button)dialog.findViewById(R.id.verifybtn);
        cancelbtn=(Button)dialog.findViewById(R.id.cancelbtn);









    }
    public void dialogFun()
    {
        dialog.show();
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    public  void btnClickFun(View view)
    {
        if(phone.getText()==null)
        {
            phone.setError("Phone number need to be filled");
        }
        else
        {
            otpVerification();
        }


    }



    public  void otpVerification()
    {
        String phoneno=phone.getText().toString();
        phoneno="+91"+phoneno;
        final String otp;
        int code=(new Random()).nextInt(6);
        final String smscode=Integer.toString(code);
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneno,smscode);
        PhoneAuthProvider phoneAuthProvider=PhoneAuthProvider.getInstance();
        phoneAuthProvider.verifyPhoneNumber(phoneno, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
              //  forceResendingToken.toString()
                Log.i("s",s);
             //   dialogFun();


            }

            @Override
            public void onVerificationCompleted(final PhoneAuthCredential phoneAuthCredential) {
                dialogFun();
                if(phoneAuthCredential.getSmsCode()!=null)
                {
                    final String otp=phoneAuthCredential.getSmsCode();
                  //  otpcode.setText(phoneAuthCredential.getSmsCode());

                    diabtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(otpcode.getText().toString().equals(phoneAuthCredential.getSmsCode()))
                            {
                                Intent intent=new Intent(getApplicationContext(),Home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),otp,Toast.LENGTH_SHORT).show();
                            //    Toast.makeText(getApplicationContext(),"invalid code entered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//                   // signInWithPhoneAuthCredential(phoneAuthCredential);
//
                }


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }


}
