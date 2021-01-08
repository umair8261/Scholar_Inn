package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_Student extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String st_name, st_reg,st_email,st_dob,st_age,st_fname,st_pnumber,st_address,st_gender;
    TextView txt_name,txt_email,txt_dob,txt_age,txt_fname,txt_pnumber,txt_address,spinner_gender,txt_reg;
    EditText edt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__student);

        txt_name=findViewById(R.id.edt_name_search);
        txt_reg=findViewById(R.id.txt_reg_search);
        txt_email=findViewById(R.id.edt_mail_search);
        txt_dob=findViewById(R.id.edt_dob_search);
        txt_age=findViewById(R.id.edt_age_search);
        txt_fname=findViewById(R.id.edt_Fname_search);
        txt_pnumber=findViewById(R.id.edt_Pnumber_search);
        txt_address=findViewById(R.id.edt_address_search);
        spinner_gender=findViewById(R.id.spinner_gender_search);


        edt_search=findViewById(R.id.edt_reg_search);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");
    }

    public void Search(View view)
    {
        if (edt_search.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Roll No. to Search", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String roll_no = edt_search.getText().toString();
            databaseReference = firebaseDatabase.getReference().child(roll_no);
            databaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.exists())
                    {
                        txt_name.setText(dataSnapshot.child("name").getValue().toString());
                        txt_reg.setText(dataSnapshot.child("reg").getValue().toString());
                        txt_email.setText(dataSnapshot.child("email").getValue().toString());
                        txt_dob.setText(dataSnapshot.child("dob").getValue().toString());
                        txt_age.setText(dataSnapshot.child("age").getValue().toString());
                        txt_fname.setText(dataSnapshot.child("fname").getValue().toString());
                        txt_pnumber.setText(dataSnapshot.child("pnumber").getValue().toString());
                        txt_address.setText(dataSnapshot.child("address").getValue().toString());
                        spinner_gender.setText(dataSnapshot.child("gender").getValue().toString());



                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Record does not exist.", Toast.LENGTH_SHORT).show();
                        txt_name.setText("");
                        txt_reg.setText("");
                        txt_email.setText("");
                        txt_age.setText("");
                        txt_dob.setText("");
                        txt_age.setText("");
                        txt_fname.setText("");
                        txt_pnumber.setText("");
                        txt_address.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {

                }
            });
        }
    }
}
