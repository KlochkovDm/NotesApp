package com.example.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private final int name;
    private final int description;
    private final int date;

    public Note(int name, int description, int date) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    protected Note(Parcel in) {
        name = in.readInt();
        date = in.readInt();
        description = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(date);
        dest.writeInt(description);
    }

    public int getName() {
        return name;
    }

}
