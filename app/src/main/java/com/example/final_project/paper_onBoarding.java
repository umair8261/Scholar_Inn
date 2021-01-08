package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class paper_onBoarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_on_boarding);

        PaperOnboardingPage scr1 = new PaperOnboardingPage("Manage Students",
                "You can manage students by performing CRUD's Operations",
                Color.parseColor("#212121"), R.drawable.icon, R.drawable.icon);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("View all Records",
                "User can view all records of Students",
                Color.parseColor("#424242"), R.drawable.icon, R.drawable.icon);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Friendly UI",
                "User-friendly describes a hardware device or software interface that is easy to use. It is \"friendly\" to the user, meaning it is not difficult to learn or understand.",
                Color.parseColor("#616161"), R.drawable.icon, R.drawable.icon);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

        FragmentManager fragmentManager=getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, onBoardingFragment);
        fragmentTransaction.commit();

        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut()
            {
                Intent intent=new Intent(getApplication(), MainActivity.class);
                startActivity(intent);

            }
        });


    }
}