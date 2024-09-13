package com.example.calculator_idk2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey
    val id: Int,
    val noteTitle: String,
    val noteDesc: String
):Parcelable

