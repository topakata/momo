package bg.tu.varna.sit.fn07062021;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bg.tu.varna.sit.fn07062021.models.Movie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MOVIE = "movie";

    // TODO: Rename and change types of parameters
    private Movie movie;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param movie Parameter 1.
     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(Movie movie) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView title = view.findViewById(R.id.titleTextView);
        title.setText(movie.getTitle());

        TextView genre = view.findViewById(R.id.genreTextView);
        genre.setText(movie.getGenre().getName());

        TextView director = view.findViewById(R.id.directorTextView);
        director.setText(movie.getDirector());

        TextView country = view.findViewById(R.id.countryTextView);
        country.setText(movie.getCountry());

        TextView description = view.findViewById(R.id.descriptionTextView);
        description.setText(movie.getDescription());

        TextView visits = view.findViewById(R.id.visitesTextView);
        visits.setText(String.valueOf(movie.getViews()));

        TextView profit = view.findViewById(R.id.profitTextView);
        profit.setText(String.format("%.2f", movie.getProfit()));

        ImageView image = view.findViewById(R.id.genreImageView);
        image.setImageResource(movie.getGenre().getImageId());

    }
}