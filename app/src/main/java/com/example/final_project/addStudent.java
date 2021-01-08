package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addStudent extends AppCompatActivity
{
    Spinner spinner_gender;
    Button btn_save ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String st_name, st_reg,st_email,st_dob,st_age,st_fname,st_pnumber,st_address,st_gender;
    Student student;
    EditText edt_name, edt_reg,edt_email,edt_dob,edt_age,edt_fname,edt_pnumber,edt_address;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        String[] arraySpinner = new String[] {"Select Gender","Male", "Female"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edt_name=findViewById(R.id.edt_name);
        edt_reg=findViewById(R.id.edt_reg);
        edt_email=findViewById(R.id.edt_mail);
        edt_dob=findViewById(R.id.edt_dob);
        edt_age=findViewById(R.id.edt_age);
        spinner_gender=findViewById(R.id.spinner_gender);
        edt_fname=findViewById(R.id.edt_Fname);
        edt_pnumber=findViewById(R.id.edt_Pnumber);
        edt_address=findViewById(R.id.edt_address);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");



    }

    public void SAVE1(View view)
    {

        databaseReference=firebaseDatabase.getReference();
        student = new Student();
        st_name = edt_name.getText().toString();
        st_reg = edt_reg.getText().toString();
        st_email = edt_email.getText().toString();
        st_dob=edt_dob.getText().toString();
        st_age=edt_age.getText().toString();
        st_fname=edt_fname.getText().toString();
        st_pnumber=edt_pnumber.getText().toString();
        st_address=edt_address.getText().toString();
        st_gender=spinner_gender.getSelectedItem().toString();

        student.setName(st_name);
        student.setReg(st_reg);
        student.setEmail(st_email);
        student.setDOB(st_dob);
        student.setAge(st_age);
        student.setFName(st_fname);
        student.setPnumber(st_pnumber);
        student.setAddress(st_address);
        student.setGender(spinner_gender);

        databaseReference.child(st_reg).setValue(student);


        Log.i("TAG", String.valueOf(databaseReference));
        Toast.makeText(this, "Data Entered!", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
    }
}
