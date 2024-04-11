package com.example.mytestquiz.di

import android.content.Context
import com.example.mytestquiz.domain.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun provideQuizRepository(@ApplicationContext appContext: Context) = QuizRepository(appContext)
}