package com.markcode.flashshareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserData extends AppCompatActivity {

    EditText UserName, FirstName, LastName, PhoneNumber, Course;
    Button SaveProfile;

    TextView ErrorMessage, RedirectLink;
    CheckBox isAcceptedTerms;

    String userName, firstName, lastName, course;
    long phoneNumber;

    String phonePattern = "^09\\d{9}$\nb";
    Pattern pattern = Pattern.compile(phonePattern);

    DatabaseReference  DataRef;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_data);

        UserName = findViewById(R.id.txt_UserName);
        FirstName = findViewById(R.id.txt_FirstName);
        LastName = findViewById(R.id.txt_LastName);
        PhoneNumber = findViewById(R.id.txt_PhoneNum);
        Course = findViewById(R.id.txt_Course);

        SaveProfile = findViewById(R.id.btn_SaveProfile);

        ErrorMessage =  findViewById(R.id.txt_ErrorMessage);

        isAcceptedTerms = findViewById(R.id.cb_TermsAndContition);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        UserID =  mAuth.getCurrentUser().getUid();

        DataRef = FirebaseDatabase.getInstance("https://flashshareapp-7488e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users");

        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidateFields())
                {

                    HashMap hashMapUserData = new HashMap();
                    hashMapUserData.put("UserName",userName);
                    hashMapUserData.put("FirstName",firstName);
                    hashMapUserData.put("LastName",lastName);
                    hashMapUserData.put("Phone",phoneNumber);
                    hashMapUserData.put("Course",course);

                    DataRef.child(UserID).child("Personal_Info").setValue(hashMapUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(UserData.this, "Successfully Saved Data", Toast.LENGTH_SHORT).show();
                            RedirectToHomaPage();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(UserData.this, "Fail to Saved Data", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });





    }

    public boolean isValidateFields ()
    {
        if (UserName.getText().toString().isEmpty())
        {
            UserName.setError("Pleas Input UserName");
            return false;
        }


        else if(FirstName.getText().toString().isEmpty())
        {
            FirstName.setError("Pleas Provide First Name");
            return false;
        }

        else if(LastName.getText().toString().isEmpty())
        {
            LastName.setError("Pleas Provide Last Name");
            return false;
        }

        else if(PhoneNumber.getText().toString().isEmpty() && containsPattern(PhoneNumber.getText().toString(),pattern))
        {
            PhoneNumber.setError("Please Enter A Valid Phone Number");
            return false;
        }

        else if(Course.getText().toString().isEmpty())
        {
            Course.setError("Enter your Course Program");
            return false;
        }
        else
        {
            userName = UserName.getText().toString();
            firstName = FirstName.getText().toString();
            lastName =  LastName.getText().toString();
            phoneNumber =Long.parseLong(PhoneNumber.getText().toString());
            course = Course.getText().toString();
            return true;
        }

    }

    private void RedirectToHomaPage()
    {
        Intent intent  = new Intent(UserData.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private boolean containsPattern (String input, Pattern pattern)
    {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }


}