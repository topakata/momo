package bg.tu.varna.sit.fn07062021.views.models;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bg.tu.varna.sit.fn07062021.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final TextView title, genre, director, country, visit, profit;
    private final ImageView image;
    private final Button edit;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleTextView);
        genre = itemView.findViewById(R.id.genreTextView);
        director = itemView.findViewById(R.id.directorTextView);
        country = itemView.findViewById(R.id.countryTextView);
        visit = itemView.findViewById(R.id.visitesTextView);
        profit = itemView.findViewById(R.id.profitTextView);

        image = itemView.findViewById(R.id.genreImageView);

        edit = itemView.findViewById(R.id.editButton);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setGenre(String genre) {
        this.genre.setText(genre);
    }

    public void setDirector(String director) {
        this.director.setText(director);
    }

    public void setCountry(String country) {
        this.country.setText(country);
    }

    public void setVisit(String visit) {
        this.visit.setText(visit);
    }

    public void setPrice(String price) {
        this.profit.setText(price);
    }

    public void setImage(int imageId) {
        this.image.setImageResource(imageId);
    }

    public void setEditAction(View.OnClickListener listener) {
        this.edit.setOnClickListener(listener);
    }

    public void setDisplayAction(View.OnClickListener listener) {
        this.itemView.setOnClickListener(listener);
    }
}
