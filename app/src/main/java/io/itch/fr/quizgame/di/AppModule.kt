package io.itch.fr.quizgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_quiz.data.QuizRepositoryImpl
import io.itch.fr.quizgame.feature_quiz.domain.SubmitQuizAnswersUseCase
import io.itch.fr.quizgame.feature_quiz.domain.SubmitQuizAnswersUseCaseImpl
import io.itch.fr.quizgame.feature_start.data.QuizRepositoryImpl
import io.itch.fr.quizgame.feature_start.domain.GetQuizQuestionsUseCase
import io.itch.fr.quizgame.feature_start.domain.GetQuizQuestionsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideQuizRepository(): io.itch.fr.quizgame.feature_start.data.QuizRepository {
        return io.itch.fr.quizgame.feature_start.data.QuizRepositoryImpl()
    }

    @Provides
    fun provideGetQuizQuestionsUseCase(repository: io.itch.fr.quizgame.feature_start.data.QuizRepository): GetQuizQuestionsUseCase {
        return GetQuizQuestionsUseCaseImpl(repository)
    }

    @Provides
    fun provideQuizRepository(): io.itch.fr.quizgame.feature_quiz.data.QuizRepository {
        return io.itch.fr.quizgame.feature_quiz.data.QuizRepositoryImpl()
    }

    @Provides
    fun provideSubmitQuizAnswersUseCase(repository: io.itch.fr.quizgame.feature_quiz.data.QuizRepository): SubmitQuizAnswersUseCase {
        return SubmitQuizAnswersUseCaseImpl(repository)
    }
}
