package io.itch.fr.quizgame.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_history.data.QuizHistoryRepository
import io.itch.fr.quizgame.feature_history.data.SharedPreferencesQuizHistoryRepository
import io.itch.fr.quizgame.feature_history.data.repositories.SharedPreferencesQuizHistoryRepository
import io.itch.fr.quizgame.feature_history.domain.QuizHistoryRepository
import io.itch.fr.quizgame.feature_quiz.data.entities.Question
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepositoryImpl
import io.itch.fr.quizgame.feature_start.domain.GetQuestionsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    // Other bindings...

    @Provides
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl(getQuestionList())
    }

    @Provides
    fun provideGetQuestionsUseCase(questionRepository: QuestionRepository): GetQuestionsUseCase {
        return GetQuestionsUseCase(questionRepository)
    }

    @Provides
    fun provideQuizHistoryRepository(context: Context): QuizHistoryRepository {
        return SharedPreferencesQuizHistoryRepository(context)
    }

    @Provides
    fun provideStartQuizUseCase(questionRepository: QuestionRepository, quizHistoryRepository: QuizHistoryRepository): StartQuizUseCase {
        return StartQuizUseCase(questionRepository, quizHistoryRepository)
    }

    private fun getQuestionList(): List<Question> {
        return listOf(
            Question("What is the capital of France?", listOf("Paris", "Rome", "London", "Berlin"), 0),
            Question("Which planet is known as the Red Planet?", listOf("Mars", "Venus", "Jupiter", "Saturn"), 0),
            Question("What is the largest ocean in the world?", listOf("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"), 0),
            Question("What is the tallest mountain in the world?", listOf("Mount Everest", "K2", "Kangchenjunga", "Makalu"), 0),
            Question("Who painted the Mona Lisa?", listOf("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"), 0)
        )
    }
}
