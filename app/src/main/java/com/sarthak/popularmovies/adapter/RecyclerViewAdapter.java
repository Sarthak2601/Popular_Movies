package com.sarthak.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarthak.popularmovies.R;
import com.sarthak.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<Viewholder> {

    private Context context;
    private Movie movie;

    public RecyclerViewAdapter(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.home_recycler_card,parent,false);
        return new Viewholder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Picasso.get().load(movie.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movie.getResult().length();
    }
}
class Viewholder extends RecyclerView.ViewHolder{
    ImageView imageView;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.cardview_imageview);
    }
}
