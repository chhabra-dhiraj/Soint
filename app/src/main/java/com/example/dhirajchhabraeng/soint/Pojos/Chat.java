package com.example.dhirajchhabraeng.soint.Pojos;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Chat implements Parcelable {

    private String personImageURL;
    private String personName;
    private String timeText;
    private Drawable seenFlag;
    private String lastMessage = "Hello Everybody";
    private int gender = -1;
    private Random random = new Random();
    private int i = random.nextInt(80);

    public Chat(String personImageURL, String personName, String timeText, Drawable seenFlag, int gender) {
        this.personImageURL = personImageURL;
        this.personName = personName;
        this.timeText = timeText;
        this.seenFlag = seenFlag;
        this.gender = gender;
    }

    protected Chat(Parcel in) {
        personImageURL = in.readString();
        personName = in.readString();
        timeText = in.readString();
        lastMessage = in.readString();
        gender = in.readInt();
        i = in.readInt();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public String getPersonImageURL() {
        if (this.gender == 0) {
            this.personImageURL = this.personImageURL + "men/" + this.i + ".jpg";
        } else if (gender == 1) {
            this.personImageURL = this.personImageURL + "women/" + this.i + ".jpg";
        }

        return personImageURL;
    }

    public String getPersonName() {
        return personName;
    }

    public String getTimeText() {
        return timeText;
    }

    public Drawable getSeenFlag() {
        return seenFlag;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(personImageURL);
        dest.writeString(personName);
        dest.writeString(timeText);
        dest.writeString(lastMessage);
        dest.writeInt(gender);
        dest.writeInt(i);
    }
}
