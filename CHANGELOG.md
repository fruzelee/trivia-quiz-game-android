# CHANGELOG

All notable changes to this project will be documented in this file.

# Quiz Game v1.0

feat: Set up the project:
- Create a new Android project using Kotlin.
- Set up the necessary dependencies for Jetpack Compose, Hilt, and Clean Architecture.

feat: Define the data models:
- Create data classes to represent the quiz questions, options, and history entries.

feat: Implement the data source:
- Create an interface for the data source, such as a repository, to handle fetching and storing data.
- Implement the repository using local storage, such as SharedPreferences, to store and retrieve the quiz questions and history entries.

feat: Design the user interface:
- Create the Start Page using Jetpack Compose, including buttons for starting the quiz and viewing history.
- Design the Quiz Page, which displays one question at a time, along with multiple-choice options.
- Implement the End Page to show the user's total score and provide options to play again or view history.
- Design the History Page to display the user's past scores fetched from the data source.

