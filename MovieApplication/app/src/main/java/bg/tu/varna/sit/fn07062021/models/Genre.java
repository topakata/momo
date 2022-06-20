package bg.tu.varna.sit.fn07062021.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Genre implements Parcelable {
    private String Name;
    private Integer ImageId;

    public Genre(String name, Integer imageId) {
        Name = name;
        ImageId = imageId;
    }

    protected Genre(Parcel in) {
        Name = in.readString();
        if (in.readByte() == 0) {
            ImageId = null;
        } else {
            ImageId = in.readInt();
        }
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public String getName() {
        return Name;
    }

    public Integer getImageId() {
        return ImageId;
    }

    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(Name, genre.Name) &&
                Objects.equals(ImageId, genre.ImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, ImageId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        if (ImageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ImageId);
        }
    }
}
