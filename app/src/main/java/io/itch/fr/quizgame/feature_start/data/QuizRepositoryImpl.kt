package io.itch.fr.quizgame.feature_start.data

class QuizRepositoryImpl : QuizRepository {
    override suspend fun getQuizQuestions(): List<QuizQuestion> {
        // Return a static list of quiz questions with multiple-choice options
        return listOf(
            QuizQuestion(
                question = "What is the capital of France?",
                options = listOf("Berlin", "London", "Paris", "Madrid"),
                correctAnswer = 2
            ),
            QuizQuestion(
                question = "Who painted the Mona Lisa?",
                options = listOf(
                    "Pablo Picasso",
                    "Leonardo da Vinci",
                    "Vincent van Gogh",
                    "Michelangelo"
                ),
                correctAnswer = 1
            ),
            QuizQuestion(
                question = "Which planet is known as the Red Planet?",
                options = listOf("Jupiter", "Mars", "Saturn", "Neptune"),
                correctAnswer = 1
            )
        )
    }
}
