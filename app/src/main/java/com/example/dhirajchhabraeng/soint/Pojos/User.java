package com.example.dhirajchhabraeng.soint.Pojos;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String currentUserUid;
    private String currentUserEmail;
    private String currentUserDisplayName;
    private Uri currentUserPhotoUrl;
    private String currentUserPhoneNumber;

    public User() {
    }

    public User(String currentUserUid, String currentUserEmail, String currentUserDisplayName) {
        this.currentUserUid = currentUserUid;
        this.currentUserEmail = currentUserEmail;
        this.currentUserDisplayName = currentUserDisplayName;
    }

    protected User(Parcel in) {
        currentUserUid = in.readString();
        currentUserEmail = in.readString();
        currentUserDisplayName = in.readString();
        currentUserPhotoUrl = in.readParcelable(Uri.class.getClassLoader());
        currentUserPhoneNumber = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getCurrentUserUid() {
        return currentUserUid;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public String getCurrentUserDisplayName() {
        return currentUserDisplayName;
    }

    public Uri getCurrentUserPhotoUrl() {
        return currentUserPhotoUrl;
    }

    public void setCurrentUserPhotoUrl(Uri currentUserPhotoUrl) {
        this.currentUserPhotoUrl = currentUserPhotoUrl;
    }

    public String getCurrentUserPhoneNumber() {
        return currentUserPhoneNumber;
    }

    public void setCurrentUserPhoneNumber(String currentUserPhoneNumber) {
        this.currentUserPhoneNumber = currentUserPhoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentUserUid);
        dest.writeString(currentUserEmail);
        dest.writeString(currentUserDisplayName);
        dest.writeParcelable(currentUserPhotoUrl, flags);
        dest.writeString(currentUserPhoneNumber);
    }
}

