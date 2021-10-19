package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.notebook.adapter.AdapterViewPage;
import com.example.notebook.fragment.FragmentNotes;
import com.example.notebook.fragment.FragmentTodo;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    boolean isOpen = true;
    TextView textView;
    Animation splash_in, splash_out;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdapterViewPage adapterViewPage;


//    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//define variable
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapterViewPage = new AdapterViewPage(getSupportFragmentManager());

//          add fragment here
        adapterViewPage.AddFragment(new FragmentTodo(), "چک لیست ");
        adapterViewPage.AddFragment(new FragmentNotes(), "یادداشت ها");

        viewPager.setAdapter(adapterViewPage);
        tabLayout.setupWithViewPager(viewPager);


        splash_in = AnimationUtils.loadAnimation(this, R.anim.splash_in);
        splash_out = AnimationUtils.loadAnimation(this, R.anim.splash_out);

//        checkBox = findViewById(R.id.checkbox_todo);


    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}
