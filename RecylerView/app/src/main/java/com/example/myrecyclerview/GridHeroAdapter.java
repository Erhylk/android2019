package com.example.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridHeroAdapter  extends RecyclerView.Adapter<GridHeroAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Hero> heroes;

    public GridHeroAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemGrid = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_grid_hero, viewGroup, false);
        return new GridHeroAdapter.GridViewHolder(itemGrid);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        Glide.with(context)
                .load(getHeroes().get(i).getPhoto())
                .apply(new RequestOptions().override(220, 350))
                .into(gridViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getHeroes().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto =itemView.findViewById(R.id.img_grid_photo);
        }
    }
}
