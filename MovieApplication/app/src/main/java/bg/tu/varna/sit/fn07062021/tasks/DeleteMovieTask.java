package bg.tu.varna.sit.fn07062021.tasks;

import android.os.Handler;

import java.util.TreeSet;

import bg.tu.varna.sit.fn07062021.listeners.OnModifiedMovieListener;
import bg.tu.varna.sit.fn07062021.models.Movie;

public class DeleteMovieTask implements Runnable {

    private OnModifiedMovieListener listener;
    private TreeSet<Movie> movies;
    private Handler handler;

    public DeleteMovieTask(Handler handler, OnModifiedMovieListener listener, TreeSet<Movie> movies) {
        this.listener = listener;
        this.movies = movies;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(movies.size() > 10) {
            listener.onDeleteMovie(movies.first());
        }

        handler.post(this);
    }
}
