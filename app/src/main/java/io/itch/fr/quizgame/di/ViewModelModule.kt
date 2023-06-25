package io.itch.fr.quizgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_history.presentation.HistoryViewModel
import io.itch.fr.quizgame.feature_quiz.presentation.QuizViewModel
import io.itch.fr.quizgame.feature_start.presentation.StartPageViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun provideStartPageViewModel(): StartPageViewModel {
        return StartPageViewModel()
    }

    @Provides
    @Singleton
    fun provideQuizViewModel(): QuizViewModel {
        return QuizViewModel()
    }

    @Provides
    @Singleton
    fun provideHistoryViewModel(): HistoryViewModel {
        return HistoryViewModel()
    }
}
