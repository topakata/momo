package bg.tu.varna.sit.fn07062021.listeners;

import bg.tu.varna.sit.fn07062021.models.Movie;

public interface OnModifiedMovieListener {
    void onAddMovie(Movie movie);
    void onDeleteMovie(Movie movie);
    void onUpdateMovie(Movie movie);
}
