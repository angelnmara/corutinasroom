package com.lamarrulla.examentecnicoandroid.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name:String,
    val image:String,
    val phone:String,
    val email:String,
    val source:String
): Parcelable