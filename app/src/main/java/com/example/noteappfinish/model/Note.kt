package com.example.noteappfinish.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @ColumnInfo(name = "noteTitle")
    val noteTitle : String?,
    @ColumnInfo(name = "note")
    val note : String?,
    @PrimaryKey(autoGenerate = true)
    var mid : Int? = null
) : Parcelable