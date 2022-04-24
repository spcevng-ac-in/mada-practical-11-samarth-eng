package com.techninja01.droidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab,fab1,fab2;
    Animation fabOpen,fabClose,rotateForward,rotateBackward;
    RelativeLayout main;
    boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab1.setVisibility(View.INVISIBLE);
                fab2.setVisibility(View.INVISIBLE);
                fab1.setClickable(false);
                fab2.setClickable(false);
                isOpen = false;
            }
        });
        main = findViewById(R.id.mainLayout);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab1.setVisibility(View.INVISIBLE);
                fab2.setVisibility(View.INVISIBLE);
                fab1.setClickable(false);
                fab2.setClickable(false);
                isOpen = false;
            }
        });
        fab = findViewById(R.id.floatingActionButton);
        fab1 = findViewById(R.id.floatingActionButton2);
        fab2 = findViewById(R.id.floatingActionButton3);
        fabOpen = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_backward);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    fab1.setVisibility(View.INVISIBLE);
                    fab2.setVisibility(View.INVISIBLE);
                    fab.startAnimation(rotateForward);
                    fab1.startAnimation(fabClose);
                    fab2.startAnimation(fabClose);
                    fab1.setClickable(false);
                    fab2.setClickable(false);
                    isOpen = false;
                }else{
                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab.startAnimation(rotateBackward);
                    fab1.startAnimation(fabOpen);
                    fab2.startAnimation(fabOpen);
                    fab1.setClickable(true);
                    fab2.setClickable(true);
                    isOpen = true;
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Place your order", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,placeOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar,menu);
        MenuCompat.setGroupDividerEnabled(menu,true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dayNnight:createAlert();break;
            case R.id.contact:callContacts();break;
        }
        return true;
    }
    public void createAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Theme Change");
        alert.setMessage("Click OK to change the theme");
        alert.setIcon(R.drawable.ic_baseline_info_24);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Changing theme", Toast.LENGTH_SHORT).show();
                int nightMode = AppCompatDelegate.getDefaultNightMode();
                int defaultMode = AppCompatDelegate.getDefaultNightMode();
                if(nightMode == AppCompatDelegate.MODE_NIGHT_YES || nightMode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Wont change the theme", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
    public void callContacts(){
        Intent intent = new Intent(MainActivity.this,ContactActivity.class);
        startActivity(intent);
    }
}