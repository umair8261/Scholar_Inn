package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update_Student extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String st_name, st_reg,st_email,st_dob,st_age,st_fname,st_pnumber,st_address,st_gender;
    Student student;
    Spinner spinner_gender;
    EditText edt_name, edt_reg,edt_email,edt_dob,edt_age,edt_fname,edt_pnumber,edt_address;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student);

        String[] arraySpinner = new String[] {"Male", "Female",};
        Spinner spinner = (Spinner) findViewById(R.id.spinner_gender_update);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edt_name=findViewById(R.id.edt_name_update);
        edt_reg=findViewById(R.id.edt_reg_update);
        edt_email=findViewById(R.id.edt_mail_update);
        edt_dob=findViewById(R.id.edt_dob_update);
        edt_age=findViewById(R.id.edt_age_update);
        edt_fname=findViewById(R.id.edt_Fname_update);
        edt_pnumber=findViewById(R.id.edt_Pnumber_update);
        edt_address=findViewById(R.id.edt_address_update);
        spinner_gender=findViewById(R.id.spinner_gender_update);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");
    }

    public void Update_DATA(View view)
    {
        if (edt_reg.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please enter the Registration no to update", Toast.LENGTH_SHORT).show();
        }
        else
        {

            String roll_no = edt_reg.getText().toString();
            st_name = edt_name.getText().toString();
            st_reg = edt_reg.getText().toString();
            st_email = edt_email.getText().toString();
            st_dob= edt_dob.getText().toString();
            st_age=edt_age.getText().toString();
            st_fname=edt_fname.getText().toString();
            st_pnumber=edt_pnumber.getText().toString();
            st_address=edt_address.getText().toString();
            st_gender=spinner_gender.getSelectedItem().toString();


            databaseReference = firebaseDatabase.getReference().child(roll_no);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        Student student = new Student();
                        student.setName(st_name);
                        student.setReg(st_reg);
                        student.setEmail(st_email);
                        student.setDOB(st_dob);
                        student.setAge(st_age);
                        student.setFName(st_fname);
                        student.setPnumber(st_pnumber);
                        student.setAddress(st_address);
                        student.setGender(spinner_gender);

                        databaseReference.setValue(student);
                        edt_name.setText("");
                        edt_reg.setText("");
                        edt_email.setText("");
                        edt_dob.setText("");
                        edt_age.setText("");
                        edt_fname.setText("");
                        edt_pnumber.setText("");
                        edt_address.setText("");

                        Toast.makeText(getApplicationContext(), "Update Succesfully!!!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Update_Student.this,Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Record not exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
