package com.cjgaliana.xamarinvideos.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by camilo on 15/06/2016.
 */
public class EvolveSession implements Parcelable {
    public static final Parcelable.Creator<EvolveSession> CREATOR = new Parcelable.Creator<EvolveSession>() {
        @Override
        public EvolveSession createFromParcel(Parcel in) {
            return new EvolveSession(in);
        }

        @Override
        public EvolveSession[] newArray(int size) {
            return new EvolveSession[size];
        }
    };

    public String Id;
    public String Title;
    public String Track;
    public String Thumbnail;
    public String Author;
    public String Description;
    public String YoutubeID;
    public String VideoUrl;
    public Date DateAdded;


    public Date DateLastModification;

    public EvolveSession() {
    }

    protected EvolveSession(Parcel in) {
        Id = in.readString();
        Title = in.readString();
        Track = in.readString();
        Thumbnail = in.readString();
        Author = in.readString();
        Description = in.readString();
        YoutubeID = in.readString();
        VideoUrl = in.readString();
        long tmpDateAdded = in.readLong();
        DateAdded = tmpDateAdded != -1 ? new Date(tmpDateAdded) : null;
        long tmpDateLastModification = in.readLong();
        DateLastModification = tmpDateLastModification != -1 ? new Date(tmpDateLastModification) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Title);
        dest.writeString(Track);
        dest.writeString(Thumbnail);
        dest.writeString(Author);
        dest.writeString(Description);
        dest.writeString(YoutubeID);
        dest.writeString(VideoUrl);
        dest.writeLong(DateAdded != null ? DateAdded.getTime() : -1L);
        dest.writeLong(DateLastModification != null ? DateLastModification.getTime() : -1L);
    }
}
