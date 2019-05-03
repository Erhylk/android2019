package com.example.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*private String[] dataName = {"Cut Nyak Dien",
            "Ki Hajar Dewantara", "R. A. Kartini"};
    */
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private ArrayList<Hero> heroes;

    private HeroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        ListView listView = findViewById(R.id.lv_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                dataName);
        listView.setAdapter(adapter);
        */

        adapter = new HeroAdapter(this);
        prepare();
        addItem();

        adapter.setHeroes(heroes);

        ListView listView = findViewById(R.id.lv_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, heroes.get(i).getName(),
                        Toast.LENGTH_SHORT).show();
                Intent objectIntent = new Intent(MainActivity.this, MyIntent.class);
                objectIntent.putExtra(MyIntent.HERO_NAME, heroes.get(i).getName());
                objectIntent.putExtra(MyIntent.HERO_DESC, heroes.get(i).getDescription());
                objectIntent.putExtra(MyIntent.HERO_PHOTO, heroes.get(i).getPhoto());
                startActivity(objectIntent);
            }
        });
    }

    private void addItem(){
        heroes = new ArrayList<>();
        for(int i = 0; i < dataName.length; i++){
            Hero hero = new Hero();
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            hero.setPhoto(dataPhoto.getResourceId(i,-1));
            heroes.add(hero);
        }

    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }
}
