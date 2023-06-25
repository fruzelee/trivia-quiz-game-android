# CHANGELOG

All notable changes to this project will be documented in this file.

# Quiz Game v1.0

feat: Initial commit of the source code for Quiz Game v1.0
feat: Add CHANGELOG file to track project changes
feat: Update .gitignore for better version control
feat(start): Add start page with "Start Quiz" and "View History" buttons
feat: Implement Hilt for dependency injection

- Added Hilt dependencies to the Gradle files
- Created Hilt Application subclass and annotated with @HiltAndroidApp
- Annotated MainActivity with @AndroidEntryPoint
- Annotated ViewModel classes with @HiltViewModel

feat: Implement Start Page feature with Hilt and Clean Architecture

- Added data models and repositories for question data
- Created GetQuestionsUseCase for fetching questions
- Updated StartPage to use HiltViewModel and invoke GetQuestionsUseCase
- Updated MainActivity to display the Start Page with Jetpack Compose

feat: Migrate Gradle files to Kotlin DSL (Kotlin KTX)

- Migrated root-level build.gradle to Kotlin DSL
- Migrated app-level build.gradle to Kotlin DSL
- Updated file extensions from .gradle to .gradle.kts

feat: Implement Quiz Page feature (feature_quiz)

- Added Question entity class for representing quiz questions
- Created QuestionDao interface for data access operations
- Implemented QuestionRepository interface using ROOM
- Created QuizViewModel for managing quiz logic and score
- Developed QuizPage composable function for displaying questions and handling user interaction
- Updated StartPage to navigate to QuizPage when "Start Quiz" button is clicked
- Updated MainActivity to use Jetpack Compose Navigation for navigation

feat: Implement Start Page feature (feature_start)

- Created StartPageViewModel for handling Start Page logic and navigation
- Developed StartPage composable function for displaying Start Page UI and handling user interaction
- Updated MainActivity to use Jetpack Compose for UI and navigate to appropriate destinations

feat: Implement Quiz Page feature (feature_quiz)

- Updated Question entity class to include userAnswerIndex field
- Updated QuestionDao interface to include update operations for questions
- Updated QuestionRepository interface to include update operations for questions
- Implemented updateQuestion operation in QuestionRepositoryImpl
- Updated QuizViewModel to handle user interaction, score calculation, and question updates
- Developed QuizPage composable function for displaying questions, handling user interaction, and showing immediate feedback

feat: Provide binding for GetQuestionsUseCase in Hilt module

- Added @Provides function in AppModule to provide GetQuestionsUseCase instance
- Updated GetQuestionsUseCase class with @Inject constructor
- Ensured that Hilt component is regenerated with the updated bindings

feat: Implement 'feature_end' screen and add timer feature on Quiz Page

- Created EndPage composable function to display quiz completion screen with total score
- Added 'Play Again' and 'View History' buttons on EndPage
- Updated QuizPage composable function to display remaining time with timerValue state
- Implemented timer functionality with startTimer, cancelTimer, and onTimerExpired callbacks in QuizViewModel
- Added onTimerExpired callback in QuizPage to handle timer expiration
