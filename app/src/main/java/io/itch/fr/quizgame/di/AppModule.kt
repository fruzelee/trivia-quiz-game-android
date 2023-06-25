package io.itch.fr.quizgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_history.data.QuizHistoryRepository
import io.itch.fr.quizgame.feature_history.data.SharedPreferencesQuizHistoryRepository
import io.itch.fr.quizgame.feature_history.data.repositories.SharedPreferencesQuizHistoryRepository
import io.itch.fr.quizgame.feature_history.domain.QuizHistoryRepository
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepositoryImpl
import io.itch.fr.quizgame.feature_start.domain.GetQuestionsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGetQuestionsUseCase(questionRepository: QuestionRepository): GetQuestionsUseCase {
        return GetQuestionsUseCase(questionRepository)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideQuizHistoryRepository(): QuizHistoryRepository {
        return SharedPreferencesQuizHistoryRepository()
    }
}
