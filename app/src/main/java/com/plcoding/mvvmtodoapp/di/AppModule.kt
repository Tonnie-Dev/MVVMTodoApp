package com.plcoding.mvvmtodoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.plcoding.mvvmtodoapp.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    //DATABASE
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):TodoDatabase{


        return Room.databaseBuilder(context,TodoDatabase::class.java,"TodoDatabase").build()


    }
    //REPOSITORY

    

}