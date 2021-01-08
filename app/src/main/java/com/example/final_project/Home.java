package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity
{
    LinearLayout add,update,search,view,remove,fee,academic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add=findViewById(R.id.Add_Stu);
        update=findViewById(R.id.Update_Stu);
        search=findViewById(R.id.Search_Stu);
        view=findViewById(R.id.View_records);
        remove=findViewById(R.id.Remove_Stu);
        fee=findViewById(R.id.fees_Stu);
        academic=findViewById(R.id.Academic_plan);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(Home.this, addStudent.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Successfull!", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Home.this, Update_Student.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Successfull!", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Home.this, Search_Student.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Successfull!", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Home.this, "Still Working!", Toast.LENGTH_SHORT).show();
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Home.this, Remove_Student.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Successfull!", Toast.LENGTH_SHORT).show();
            }
        });

        fee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Home.this, "Still Working!", Toast.LENGTH_SHORT).show();
            }
        });

        academic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Home.this, "Still Working!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
