package com.techninja01.droidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    String[] sendTo = {"apoorva.me1@gmail.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_TEXT,"Kon\'nichiwa");
                intent.putExtra(Intent.EXTRA_SUBJECT,"I wish to tell you that...");
                intent.putExtra(Intent.EXTRA_EMAIL,sendTo);
                startActivity(Intent.createChooser(intent,"Select before the curse attack..."));
            }
        });
        Animation animation = AnimationUtils.loadAnimation(ContactActivity.this,R.anim.blink);
        textView = findViewById(R.id.textView);
        textView.setText("Click here to read the message");
        textView.startAnimation(animation);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.about);
                textView.clearAnimation();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}