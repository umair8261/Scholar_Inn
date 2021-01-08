package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Remove_Student extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__student);

        edt_reg=findViewById(R.id.edt_reg_delete);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");
    }

    public void Delete_DATA(View view)
    {
        if (edt_reg.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Registration No. to Delete.", Toast.LENGTH_SHORT).show();
        }
        else
        {

            String roll_no = edt_reg.getText().toString();
            databaseReference = firebaseDatabase.getReference().child(roll_no);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.exists())
                    {
                        databaseReference.getRef().removeValue();
                        Toast.makeText(getApplicationContext(), "Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Record not Found Try Again later!", Toast.LENGTH_SHORT).show();
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
