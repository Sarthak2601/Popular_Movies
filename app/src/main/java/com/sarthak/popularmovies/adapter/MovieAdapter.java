package com.sarthak.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarthak.popularmovies.MainActivityInterface;
import com.sarthak.popularmovies.R;
import com.sarthak.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    private ArrayList<Movie> Movies = new ArrayList<>();
    private Context context;
    private MainActivityInterface activityInterface;
    private static final String BASE_URL = "http://image.tmdb.org/t/p/w185";

    public MovieAdapter(MainActivityInterface mainActivityInterface) {
        this.activityInterface = mainActivityInterface;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.cardview_imageview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            activityInterface.startDetailActivity(Movies.get(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.home_recycler_card,parent,false);
        return new Viewholder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String posterPath = Movies.get(position).getImage();
        if(posterPath.equals("null")) holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        else Picasso.get().load(BASE_URL + posterPath).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Movies.size();
    }

    public void setMovies(ArrayList<Movie> movies){
        Movies = movies;
        notifyDataSetChanged();
    }
}
