package bg.tu.varna.sit.fn07062021.listeners;

import bg.tu.varna.sit.fn07062021.models.Movie;

public interface OnClickMovieListener {
    void onClickDisplayMovie(Movie movie);
    void onClickEditMovie(Movie movie, int position);
}
