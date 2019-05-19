package com.example.viewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private WeatherAdapter adapter;
    private EditText edtCity;
    private ProgressBar progressBar;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        edtCity = findViewById(R.id.editCity);
        progressBar = findViewById(R.id.progressBar);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getListWeathers().observe(this, getWeather);

        findViewById(R.id.btnCity).setOnClickListener(myListener);
    }

    private Observer<ArrayList<WeatherItems>> getWeather = new Observer<ArrayList<WeatherItems>>() {
        @Override
        public void onChanged(ArrayList<WeatherItems> weatherItems) {
            if (weatherItems != null) {
                adapter.setData(weatherItems);
                showLoading(false);
            }
        }
    };

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String city = edtCity.getText().toString();
            if (TextUtils.isEmpty(city)) return;
            mainViewModel.setWeather(city);
            showLoading(true);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}