package com.cjgaliana.xamarinvideos.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by camilo on 15/06/2016.
 */
public class VideoCollection implements Parcelable {

    public String Id;
    public String Name;
    public String Description;
    public String Website;
    public List<EvolveSession> Videos;
    public Date DateAdded;
    public Date DateLastModification;
    public String FileName;

    protected VideoCollection(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Description = in.readString();
        Website = in.readString();
        if (in.readByte() == 0x01) {
            Videos = new ArrayList<EvolveSession>();
            in.readList(Videos, EvolveSession.class.getClassLoader());
        } else {
            Videos = null;
        }
        long tmpDateAdded = in.readLong();
        DateAdded = tmpDateAdded != -1 ? new Date(tmpDateAdded) : null;
        long tmpDateLastModification = in.readLong();
        DateLastModification = tmpDateLastModification != -1 ? new Date(tmpDateLastModification) : null;
        FileName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeString(Description);
        dest.writeString(Website);
        if (Videos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(Videos);
        }
        dest.writeLong(DateAdded != null ? DateAdded.getTime() : -1L);
        dest.writeLong(DateLastModification != null ? DateLastModification.getTime() : -1L);
        dest.writeString(FileName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VideoCollection> CREATOR = new Parcelable.Creator<VideoCollection>() {
        @Override
        public VideoCollection createFromParcel(Parcel in) {
            return new VideoCollection(in);
        }

        @Override
        public VideoCollection[] newArray(int size) {
            return new VideoCollection[size];
        }
    };
}