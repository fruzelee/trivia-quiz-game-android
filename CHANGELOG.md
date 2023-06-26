# CHANGELOG

All notable changes to this project will be documented in this file.

# Quiz Game v1.0

feat: Set up the project:

- Create a new Android project using Kotlin.
- Set up the necessary dependencies for Jetpack Compose, Hilt, and Clean Architecture.

feat: Define the data models:

- Create data classes to represent the quiz questions, options, and history entries.

feat: Implement the data source:

- Create an interface for the data source, such as a repository, to handle fetching and storing
  data.
- Implement the repository using local storage, such as SharedPreferences, to store and retrieve the
  quiz questions and history entries.

feat: Design the user interface:

- Create the Start Page using Jetpack Compose, including buttons for starting the quiz and viewing
  history.
- Design the Quiz Page, which displays one question at a time, along with multiple-choice options.
- Implement the End Page to show the user's total score and provide options to play again or view
  history.
- Design the History Page to display the user's past scores fetched from the data source.

Implement the quiz logic:

- Track the user's score using a variable that increments for correct answers and decrements for
  incorrect answers.
- Implement a countdown timer for each question. If the timer expires, consider the answer incorrect
  and deduct a point.
- Handle the user's answer selection and provide immediate feedback on correctness.
- Proceed to the next question after the user selects an answer or the timer runs out.

feat: Implement navigation:

- Use Jetpack Navigation to handle navigation between the different screens (Start Page, Quiz Page,
  End Page, and History Page).

feat: Integrate dependency injection with Hilt:

- Configure Hilt for dependency injection to provide necessary dependencies, such as the repository,
  to different parts of the application.

feat: Handle data persistence:

- Integrate the repository with local storage (e.g., SharedPreferences) to persist and retrieve the
  quiz history.

feat: Testing:

- Write unit tests to ensure the correctness of individual components, such as the quiz logic,
  scoring, and data source.
- Write UI tests to verify the correct behavior of user interactions and navigation.

feat: Polish and optimize the application:

- Refactor the codebase following Clean Architecture principles for separation of concerns and
  maintainability.
- Optimize the application for performance and responsiveness.
- Handle edge cases, such as no score going into negatives and handling different device
  orientations.

feat: Implement navigation to End Page upon quiz completion

feat: Add score counting and display on EndPage

fix: a minor bug

feat: Add new quiz questions

fix: Resolve issue with quiz history not showing

This commit fixes an issue where the quiz history was not being displayed. The problem was caused by
the missing implementation of the `getHistoryListFromPreferences` function. The function has been
added to properly retrieve the history list from shared preferences and display it. Now, the quiz
history will be shown correctly in the app.

test: Update test cases for QuizViewModelTest

This commit updates the test cases in the QuizViewModelTest class to ensure they reflect the changes
made to the QuizPageViewModel class. The test cases have been revised and adjusted to cover the
updated functionality and behavior of the view model.

fix: Provide DispatcherProvider dependency in Hilt module

According to the error message, the `DispatcherProvider` dependency was missing a binding in the
Hilt component. This commit adds a `@Provides`-annotated method in the appropriate Hilt module to
provide the `DispatcherProvider` dependency. With this change, the missing binding error is
resolved.

This commit addresses the issue by providing the necessary dependency and ensures the proper
functioning of the application.

fix: Resolve bug preventing replay of the quiz after completion

In the QuizPage, modified the logic for quiz completion to resolve a bug that prevented users from
playing the quiz again from the end page. Removed the invocation of the onQuizFinished callback,
which was causing the issue. Now, users can successfully replay the quiz after completing it.

build: Update Gradle configuration

Updated the Gradle configuration to the latest version. This includes updating the dependencies and
build tools to their latest versions, ensuring compatibility and taking advantage of new features
and improvements.

fix: prevent negative scores in scoring logic

This commit fixes a bug in the scoring logic of the QuizPageViewModel that allowed negative scores.
Now, when a user selects an incorrect answer, the score is properly deducted, but it is prevented
from going below zero. This ensures that the score remains non-negative throughout the game.

feat: Add live score display on the QuizPage

Updated the QuizPage to display the score live during the quiz. The score is now shown in real-time
at the top of the screen. When the quiz is completed, the final score is displayed on the QuizResult
screen. Additionally, the score is saved to the history along with the date and time of completion.

fix: Correct score calculation on timer expiration

Resolved the issue where the score was calculated incorrectly when the timer expired. Previously,
the score was being decremented by 1, resulting in a negative score. The score calculation has been
updated to deduct 1 only if the updated score is greater than or equal to 0. This ensures that the
score does not become negative when the timer runs out.

feat: Update navigation in quizPageViewModel and NavGraph

Refactored the navigation logic in the QuizPageViewModel to handle the quiz finished event and
navigate to the "endPage" destination with the score. Updated the addQuizPage function in the
NavGraph to include the popUpTo modifier, ensuring the back stack is cleared up to the "quiz"
destination when finishing the quiz.
