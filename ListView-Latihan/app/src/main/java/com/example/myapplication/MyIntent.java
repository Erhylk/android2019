package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyIntent extends AppCompatActivity {

    public static final String HERO_PHOTO = "photo";
    public static final String HERO_NAME = "name";
    public static final String HERO_DESC = "description";
    private TextView txtName;
    private TextView txtDescription;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        txtName = findViewById(R.id.txt_name);
        txtDescription = findViewById(R.id.txt_description);
        imgPhoto = findViewById(R.id.img_photo);

        txtName.setText(getIntent().getStringExtra(HERO_NAME));
        txtDescription.setText(getIntent().getStringExtra(HERO_DESC));
        imgPhoto.setImageResource(getIntent().getIntExtra(HERO_PHOTO, 0));
    }
}
