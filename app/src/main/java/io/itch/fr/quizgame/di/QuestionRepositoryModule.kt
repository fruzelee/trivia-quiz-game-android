package io.itch.fr.quizgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
class QuestionRepositoryModule {
    @Provides
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl() // Replace with the actual implementation of QuestionRepository
    }
}
