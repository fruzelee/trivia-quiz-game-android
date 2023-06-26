package io.itch.fr.quizgame.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.itch.fr.quizgame.repository.QuizRepository
import io.itch.fr.quizgame.repository.QuizRepositoryImpl
import io.itch.fr.quizgame.utils.DefaultDispatcherProvider
import io.itch.fr.quizgame.utils.DispatcherProvider

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideQuizRepository(context: Context): QuizRepository {
        return QuizRepositoryImpl(context)
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    // Provide other dependencies if needed

}
