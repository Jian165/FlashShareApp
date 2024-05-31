package com.markcode.flashshareapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button login;

    EditText logInputEmail, logInputPassword;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    ProgressDialog progressDialog;

    ImageView btnGoogle, btnPersonalMail;

    DatabaseReference DatabaseRef;


    CheckBox seelogPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        login =  findViewById(R.id.btn_Login);
        logInputEmail= findViewById(R.id.edt_L_inputEmail);
        logInputPassword = findViewById(R.id.edt_L_password);
        seelogPassword =  findViewById(R.id.cb_L_seePassword);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnGoogle = findViewById(R.id.btn_google);
        btnPersonalMail =  findViewById(R.id.btn_CustomMail);

        DatabaseRef = FirebaseDatabase.getInstance("https://flashshareapp-7488e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Test");


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnPersonalMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //performLogin();
                DatabaseRef.setValue("test");

            }
        });

        seelogPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    logInputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {

                    logInputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GoogleSignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private void performLogin() {
        String email = logInputEmail.getText().toString();
        String password = logInputPassword.getText().toString();

        if(!email.matches(emailPattern))
        {
            logInputEmail.setError("Enter Correct Email");
        }
        else if (password.isEmpty() || password.length()<6)
        {
            logInputPassword.setError("Enter Proper Password");
        }
        else
        {
            progressDialog.setMessage("Please Wait While login");
            progressDialog.setTitle("login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}