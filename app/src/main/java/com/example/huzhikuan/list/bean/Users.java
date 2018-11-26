package com.example.huzhikuan.list.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    public int userId;
    public String userName;
    public boolean isMale;

    public Users(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected Users(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt()==1;

    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale ? 1 : 0);
    }
}
