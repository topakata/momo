package bg.tu.varna.sit.fn07062021.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bg.tu.varna.sit.fn07062021.R;
import bg.tu.varna.sit.fn07062021.models.Genre;
import bg.tu.varna.sit.fn07062021.models.Movie;

public class MovieSource {

    private static final List<Genre> genres = new ArrayList<>();
    private static final double rangeMin = 3;
    private static final double rangeMax = 100;
    private static final String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

    static {
        genres.add(new Genre("Action", R.drawable.action));
        genres.add(new Genre("Adventure", R.drawable.adventure));
        genres.add(new Genre("Animation", R.drawable.animation));
        genres.add(new Genre("Comedy", R.drawable.comedy));
        genres.add(new Genre("Drama", R.drawable.drama));
        genres.add(new Genre("Family", R.drawable.family));
        genres.add(new Genre("Home movies", R.drawable.home_movies));
        genres.add(new Genre("Horror", R.drawable.horror));
        genres.add(new Genre("Music videos", R.drawable.music_videos));
        genres.add(new Genre("Science fiction", R.drawable.science_fiction));
    }

    public static List<Movie> generateMovies(int count) {
        List<Movie> movies = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            movies.add(
                    new Movie(
                            "Movie title " + i,
                            "Director Director" + i,
                            "Country " + i,
                            description.substring(0, random.nextInt(description.length())),
                            rangeMin + (rangeMax - rangeMin) * random.nextDouble(),
                            genres.get(random.nextInt(10))
                    )
            );
        }
        return movies;
    }

    public static List<Genre> generateGenre() {
        return genres;
    }

    public static int getGenrePosition(Genre genre) {
        return genres.indexOf(genre);
    }
}
