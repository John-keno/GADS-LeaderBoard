package com.github.johnkeno.gadsleadersboard;

import android.content.Intent;
import android.os.Bundle;

import com.github.johnkeno.gadsleadersboard.ui.main.FragmentLearn;
import com.github.johnkeno.gadsleadersboard.ui.main.FragmentSkillIQ;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.johnkeno.gadsleadersboard.ui.main.TabsPagerAdapter;

public class MainActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private TabsPagerAdapter mTabsPagerAdapter;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        //Initializing the fragments

        mTabsPagerAdapter.AddFragment(new FragmentLearn(),getString(R.string.Tab1_title));
        mTabsPagerAdapter.AddFragment(new FragmentSkillIQ(),getString(R.string.Tab2_title));

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem submit = menu.findItem(R.id.action_submit);
        ConstraintLayout submitLayout = (ConstraintLayout) submit.getActionView();
        mButton = submitLayout.findViewById(R.id.submit);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(submit);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_submit) {
            startActivity(new Intent(this, SubmitActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}