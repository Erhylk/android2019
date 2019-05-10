package com.example.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListHeroAdapter extends  RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<Hero> heroes;

    public ListHeroAdapter(Context context) {
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemGrid = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_hero, viewGroup, false);
        return new ListViewHolder(itemGrid);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        listViewHolder.tvName.setText(getHeroes().get(i).getName());
        listViewHolder.tvDescription.setText(getHeroes().get(i).getDescription());

        Glide.with(context)
                .load(getHeroes().get(i).getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(listViewHolder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return getHeroes().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
