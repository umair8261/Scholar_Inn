package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth FA;
    EditText edt_mail,edt_pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_mail=findViewById(R.id.edt_username);
        edt_pass=findViewById(R.id.edt_password);

        btn_login = findViewById(R.id.btn_login);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        FA=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email,password;
                if ((edt_mail.getText().toString()).equals("") || (edt_pass.getText().toString()).equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                    Log.d("USER_DATA","Please Enter Credentials " );
                }
                else
                {
                    email=edt_mail.getText().toString();
                    password=edt_pass.getText().toString();
                    Log.d("USER_DATA","Email  : " + email + "  PAss : " + password  );
                    FA.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(MainActivity.this,
                                    new OnCompleteListener<AuthResult>() {
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

                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText("Loading");
                                            pDialog.setCancelable(false);
                                            pDialog.show();

                                            Toast.makeText(MainActivity.this, "Login As  " + u_email, Toast.LENGTH_SHORT).show();

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                                    startActivity(intent);
                                                }
                                            }, 1500);


                                        }
                                    });
                }
            }
        });


    }


    public void signUp(View view)
    {
        Intent intent=new Intent(this, Signup_Activity.class);
        startActivity(intent);
    }

    public void LogIn(View view)
    {
        final String email,password;
        email=edt_mail.getText().toString();
        password=edt_pass.getText().toString();

        Log.d("USER_DATA","Email  : " + email + "  PAss : " + password  );
        FA.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {

                    String u_email,uid;
                    FirebaseUser firebaseUser=FA.getCurrentUser();
                    u_email= firebaseUser.getEmail();
                    uid= firebaseUser.getUid();
                    Log.d("USER_DATA","Email  : " + u_email + "  PAss : " + uid  );
                    HashMap<String,String> user = new HashMap<>();
                    user.put("Email",u_email);
                    user.put("Uid",uid);
                    databaseReference.child(uid).setValue(user);
                    Intent intent=new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });


    }
}
