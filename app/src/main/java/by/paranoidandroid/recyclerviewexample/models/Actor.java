package by.paranoidandroid.recyclerviewexample.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Actor implements Parcelable {
    private String name;
    private Uri thumb;
    private Uri avatar;
    private String biography;

    public Actor(String name, Uri thumb, Uri avatar, String biography) {
        this.name = name;
        this.thumb = thumb;
        this.avatar = avatar;
        this.biography = biography;
    }

    public Actor(Parcel parcel) {
        String[] data = new String[4];
        parcel.readStringArray(data);
        this.name = data[0];
        this.thumb = Uri.parse(data[1]);
        this.avatar = Uri.parse(data[2]);
        this.biography = data[3];
    }

    public String getName() {
        return name;
    }

    public Uri getThumb() {
        return thumb;
    }

    public Uri getAvatar() {
        return avatar;
    }

    public String getBiography() {
        return biography;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringArray(new String[] {name, thumb.toString(), avatar.toString(), biography});
    }

    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {

        @Override
        public Actor createFromParcel(Parcel source) {
            return new Actor(source);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
