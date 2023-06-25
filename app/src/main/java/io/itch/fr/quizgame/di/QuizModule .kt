package io.itch.fr.quizgame.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.repository.QuizRepositoryImpl
@Module
@InstallIn(ActivityComponent::class)
object QuizModule {
    @Provides
    fun provideQuizRepository(context: Context): QuizRepository {
        return QuizRepositoryImpl(context)
    }
}
