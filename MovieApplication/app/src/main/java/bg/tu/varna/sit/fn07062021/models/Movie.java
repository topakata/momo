package bg.tu.varna.sit.fn07062021.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;
import java.util.UUID;

public class Movie implements Parcelable, Comparable<Movie> {

    private UUID uuid;
    private String title, director, country, description;
    private Integer views;
    private Double profit, ticketPrice;
    private Genre genre;

    public Movie(String title, String director, String country, String description, Double ticketPrice, Genre genre) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.director = director;
        this.country = country;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.genre = genre;
        this.profit = 0.0;
        this.views = 0;
    }

    protected Movie(Parcel in) {
        uuid = (UUID) in.readSerializable();
        title = in.readString();
        director = in.readString();
        country = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            views = null;
        } else {
            views = in.readInt();
        }
        if (in.readByte() == 0) {
            profit = null;
        } else {
            profit = in.readDouble();
        }
        if (in.readByte() == 0) {
            ticketPrice = null;
        } else {
            ticketPrice = in.readDouble();
        }
        genre = in.readParcelable(Genre.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Integer getViews() {
        return views;
    }

    public Double getProfit() {
        return profit;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public Genre getGenre() {
        return genre;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void addViews(Integer views) {
        this.views += views;
        this.profit = this.views*this.ticketPrice;
    }

    @Override
    public int compareTo(Movie o) {
        int result = profit.compareTo(o.getProfit());

        if(result == 0) {
            result = Integer.compare(hashCode(), o.hashCode());
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return uuid.equals(movie.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(uuid);
        dest.writeString(title);
        dest.writeString(director);
        dest.writeString(country);
        dest.writeString(description);
        if (views == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(views);
        }
        if (profit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(profit);
        }
        if (ticketPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(ticketPrice);
        }
        dest.writeParcelable(genre, flags);
    }
}
