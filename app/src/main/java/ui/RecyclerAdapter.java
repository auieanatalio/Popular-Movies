package ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auie.popularmovies.MovieDetails;
import com.auie.popularmovies.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import model.Movie;
import model.MovieResponse;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserViewHolder> {

    Context context;
    List<Movie> movieList;


    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_adapter, parent, false);
        UserViewHolder ViewHolder = new UserViewHolder(view);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.title.setText("Title: " + movieList.get(position).getTitle());

        holder.voteAverage.setText("Average Vote: " + String.valueOf((movieList.get(position).getVote_average())));
        holder.releaseDate.setText("Release Date: " + movieList.get(position).getRelease_date());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/" + movieList.get(position).getBackdrop_path())
                .into(holder.backrgoundPath);

    }

    public void setMovieList(List<Movie> movies){
        movieList = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();

    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView voteAverage;
        TextView releaseDate;
        ImageView backrgoundPath;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            title = itemView.findViewById(R.id.title);
            voteAverage = itemView.findViewById(R.id.vote_average);
            releaseDate = itemView.findViewById(R.id.release_date);
            backrgoundPath = itemView.findViewById(R.id.bg_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, MovieDetails.class);
                    intent.putExtra("title", movieList.get(position).getTitle());
                    intent.putExtra("overview", movieList.get(position).getOverview());
                    intent.putExtra("poster_path", movieList.get(position).getPoster_path());
                    context.startActivity(intent);

                }
            });

        }
    }
}
