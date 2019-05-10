package com.example.myrecyclerview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.os.Looper.prepare;

public class MainActivity extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    final String STATE_TITLE = "state_string";
    final String STATE_MODE = "state_mode";
    private int mode;
    RecyclerView rvHeroes;
    private ArrayList<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepare();
        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        if (savedInstanceState == null) {
            setActionBarTitle("Mode List");
            showListRecycler();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            int stateMode = savedInstanceState.getInt(STATE_MODE);

            setActionBarTitle(stateTitle);
            setMode(stateMode);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void showListRecycler(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter adapter = new ListHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);
    }

    private void showGridRecycler() {
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter adapter = new GridHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);
    }

    private void showCardRecycler() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardHeroAdapter adapter = new CardHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);
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
    private void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode){
            case R.id.action_list:
                title = "Mode List";
                showListRecycler();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showGridRecycler();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showCardRecycler();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putInt(STATE_MODE, mode);
    }
}
