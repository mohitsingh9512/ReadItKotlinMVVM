package com.apprensics.readit

import android.app.Application
import androidx.room.Room
import com.apprensics.readit.repository.db.AppDatabase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        lateinit var retrofit : Retrofit
        lateinit var appDatabase : AppDatabase

    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://www.mocky.io/v2/")
            .build()
        appDatabase = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,"app-database").build()

    }
}