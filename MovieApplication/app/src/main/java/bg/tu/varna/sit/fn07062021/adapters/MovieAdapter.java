package bg.tu.varna.sit.fn07062021.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.TreeSet;

import bg.tu.varna.sit.fn07062021.R;
import bg.tu.varna.sit.fn07062021.listeners.OnClickMovieListener;
import bg.tu.varna.sit.fn07062021.models.Movie;
import bg.tu.varna.sit.fn07062021.views.models.MovieViewHolder;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private TreeSet<Movie> movies;
    private final OnClickMovieListener listener;

    public MovieAdapter(OnClickMovieListener listener, List<Movie> movies) {
        this.movies = new TreeSet<>(movies);
        this.listener = listener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = (Movie) movies.toArray()[position];

        holder.setTitle(movie.getTitle());
        holder.setGenre(movie.getGenre().getName());
        holder.setDirector(movie.getDirector());
        holder.setCountry(movie.getCountry());
        holder.setVisit(String.valueOf(movie.getViews()));
        holder.setPrice(String.format("%.2f", movie.getProfit()));
        holder.setImage(movie.getGenre().getImageId());

        holder.setEditAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickEditMovie(movie, position);
            }
        });

        holder.setDisplayAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDisplayMovie(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public TreeSet<Movie> getItems() {
        return this.movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void updateMovie(Movie movie) {
        movies.remove(movie);
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void delete(Movie movie) {
        movies.remove(movie);
        notifyItemRemoved(0);
    }
}
