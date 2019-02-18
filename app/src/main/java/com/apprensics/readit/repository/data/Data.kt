package com.apprensics.readit.repository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
        @PrimaryKey
        @ColumnInfo(name = "objectId")
        val objectId : String,
        @ColumnInfo(name = "category")
        val category : String,
        @ColumnInfo(name = "title")
        val title : String,
        @ColumnInfo(name = "body")
        val body : String,
        @ColumnInfo(name = "imageUrl")
        val imageUrl : String,
        @ColumnInfo(name = "question")
        val question : String
)
