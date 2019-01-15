package com.example.dhirajchhabraeng.soint.Pojos;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {
    private Uri personImageURL;
    private String personName;
    private String personEmail;

    public Contacts(Uri personImageURL, String personName, String personEmail) {
        this.personImageURL = personImageURL;
        this.personName = personName;
        this.personEmail = personEmail;
    }

    protected Contacts(Parcel in) {
        personImageURL = in.readParcelable(Uri.class.getClassLoader());
        personName = in.readString();
        personEmail = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public Uri getPersonImageURL() {
        return personImageURL;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(personImageURL, flags);
        dest.writeString(personName);
        dest.writeString(personEmail);
    }
}
