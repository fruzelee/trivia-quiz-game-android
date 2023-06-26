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
- Refactor the codebase following Clean Architecture principles for separation of concerns and maintainability.
- Optimize the application for performance and responsiveness.
- Handle edge cases, such as no score going into negatives and handling different device orientations.

feat: Implement navigation to End Page upon quiz completion