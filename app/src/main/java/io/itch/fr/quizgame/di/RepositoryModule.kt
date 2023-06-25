package io.itch.fr.quizgame.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.repository.QuizRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideQuizRepository(context: Context): QuizRepository {
        return QuizRepositoryImpl(context)
    }
}

