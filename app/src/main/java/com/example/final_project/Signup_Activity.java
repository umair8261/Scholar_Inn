package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup_Activity extends AppCompatActivity {

    Button btn_signup;
    FirebaseAuth FA;
    EditText edt_mail,edt_pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        FA = FirebaseAuth.getInstance();
        btn_signup = findViewById(R.id.btn_signup2);

        edt_mail = findViewById(R.id.edt_username2);
        edt_pass = findViewById(R.id.edt_password2);

        btn_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String email, password;
                email = edt_mail.getText().toString();
                password = edt_pass.getText().toString();

                Log.d("USER_DATA", "Email  : " + email + "  PAss : " + password);
                FA.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup_Activity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                            String u_email, uid;
                            FirebaseUser firebaseUser = FA.getCurrentUser();
                            u_email = firebaseUser.getEmail();
                            uid = firebaseUser.getUid();
                            Log.d("USER_DATA", "Email  : " + u_email + "  PAss : " + uid);
                            HashMap<String, String> user = new HashMap<>();
                            user.put("Email", u_email);
                            user.put("Uid", uid);
                            databaseReference.child(uid).setValue(user);
                            Intent intent = new Intent(Signup_Activity.this, MainActivity.class);
                            startActivity(intent);

                    }
                });
            }
        });
    }
}
