package bg.tu.varna.sit.fn07062021;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import bg.tu.varna.sit.fn07062021.data.MovieSource;
import bg.tu.varna.sit.fn07062021.models.Genre;
import bg.tu.varna.sit.fn07062021.models.Movie;

public class EditActivity extends BaseActivity {

    private EditText title, director, country, description, ticketPrice;
    private Spinner genre;
    private Button save, back;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_dialod);

        initViews();
        loadMovie();
    }

    private void loadMovie() {
        Intent intent = getIntent();
        if(intent != null) {
            movie = intent.getParcelableExtra(ARG_MOVIE);
            if(movie != null) {
                title.setText(movie.getTitle());
                director.setText(movie.getDirector());
                country.setText(movie.getCountry());
                description.setText(movie.getDescription());
                ticketPrice.setText(String.valueOf(movie.getProfit()));
                int position = MovieSource.getGenrePosition(movie.getGenre());
                genre.setSelection(position);
            }
        }
    }

    private void initViews() {
        title = findViewById(R.id.title);
        director = findViewById(R.id.director);
        country = findViewById(R.id.country);
        description = findViewById(R.id.description);
        ticketPrice = findViewById(R.id.ticketPrice);

        genre = findViewById(R.id.genre);
        ArrayAdapter<Genre> genreArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MovieSource.generateGenre());
        genreArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(genreArrayAdapter);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        save = findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(back.getId() == v.getId()) {
            finish();
        } if (save.getId() == v.getId()) {
            Movie movie = onBindEditMovie();
            if(movie != null) {
                Intent intent = new Intent();
                intent.putExtra(ARG_MOVIE, movie);
                setResult(RETURN_CODE_EDIT, intent);
                finish();
            }
        }
    }

    private Movie onBindEditMovie() {
        if(!isAddedMovie() && movie != null) {
            movie.setTitle(title.getText().toString());
            movie.setDirector(director.getText().toString());
            movie.setCountry(country.getText().toString());
            movie.setDescription(description.getText().toString());
            movie.setTicketPrice(Double.parseDouble(ticketPrice.getText().toString()));
            movie.setGenre((Genre) genre.getSelectedItem());
        }
        return movie;
    }

    private boolean isAddedMovie() {
        return title.getText().toString().isEmpty() ||
                director.getText().toString().isEmpty()  ||
                country.getText().toString().isEmpty()  ||
                description.getText().toString().isEmpty()  ||
                ticketPrice.getText().toString().isEmpty() ||
                genre.isSelected();
    }
}