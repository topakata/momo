package bg.tu.varna.sit.fn07062021;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import bg.tu.varna.sit.fn07062021.data.MovieSource;
import bg.tu.varna.sit.fn07062021.listeners.OnModifiedMovieListener;
import bg.tu.varna.sit.fn07062021.models.Genre;
import bg.tu.varna.sit.fn07062021.models.Movie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText title, director, country, description, ticketPrice;
    Spinner genre;
    private Button save, back;
    private OnModifiedMovieListener listener;

    public AddDialogFragment() {
        // Required empty public constructor
    }

    public static AddDialogFragment newInstance() {
        return new AddDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_dialod, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnModifiedMovieListener) {
            listener = (OnModifiedMovieListener) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.title);
        director = view.findViewById(R.id.director);
        country = view.findViewById(R.id.country);
        description = view.findViewById(R.id.description);
        ticketPrice = view.findViewById(R.id.ticketPrice);

        genre = view.findViewById(R.id.genre);
        ArrayAdapter<Genre> genreArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, MovieSource.generateGenre());
        genreArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(genreArrayAdapter);

        back = view.findViewById(R.id.back);
        back.setOnClickListener(this);

        save = view.findViewById(R.id.save);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == save.getId()) {

            if(!isAddedMovie() && listener != null) {
                Movie movie = new Movie(
                        title.getText().toString(),
                        director.getText().toString(),
                        country.getText().toString(),
                        description.getText().toString(),
                        Double.parseDouble(ticketPrice.getText().toString()),
                        (Genre) genre.getSelectedItem());
                listener.onAddMovie(movie);
                dismiss();
            }
        } if(v.getId() == back.getId()) {
            dismiss();
        }
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