package io.itch.fr.quizgame.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.feature_quiz.data.repositories.QuestionRepository
import io.itch.fr.quizgame.feature_start.domain.GetQuestionsUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Other bindings...

    @Provides
    fun provideGetQuestionsUseCase(questionRepository: QuestionRepository): GetQuestionsUseCase {
        return GetQuestionsUseCase(questionRepository)
    }
}
