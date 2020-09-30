package com.ssafy.howdomodo.data.datasource.model

import android.os.Parcel
import android.os.Parcelable


data class Users constructor(
        var userCode: String,
        var userEmail: String,
        var userName: String,
        var userNick: String,
        var userPw: String,
        var userGender: Int,
        var userBirth: String
): Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readInt(),
                parcel.readString().toString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(userCode)
                parcel.writeString(userEmail)
                parcel.writeString(userName)
                parcel.writeString(userNick)
                parcel.writeString(userPw)
                parcel.writeInt(userGender)
                parcel.writeString(userBirth)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Users> {
                override fun createFromParcel(parcel: Parcel): Users {
                        return Users(parcel)
                }

                override fun newArray(size: Int): Array<Users?> {
                        return arrayOfNulls(size)
                }
        }
}