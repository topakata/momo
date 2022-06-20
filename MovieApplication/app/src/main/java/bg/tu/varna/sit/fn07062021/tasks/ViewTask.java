package bg.tu.varna.sit.fn07062021.tasks;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import bg.tu.varna.sit.fn07062021.listeners.OnModifiedMovieListener;
import bg.tu.varna.sit.fn07062021.models.Movie;

public class ViewTask implements Runnable {

    private Handler handler;
    private OnModifiedMovieListener listener;
    private TreeSet<Movie> movies;

    public ViewTask(Handler handler, OnModifiedMovieListener listener, TreeSet<Movie> movies) {
        this.listener = listener;
        this.movies = movies;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        for (Movie movie : new ArrayList<>(movies)) {
            movie.addViews(random.nextInt(1000) + 1);
            listener.onUpdateMovie(movie);
        }

        handler.postDelayed(this,1000);
    }
}
