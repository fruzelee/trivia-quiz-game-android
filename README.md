# Quiz Game

Welcome to the Quiz Game! This project is a simple quiz application built using [Compose](https://developer.android.com/jetpack/compose) and [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

## Table of Contents

- [Architecture](#architecture)
- [Functionality](#functionality)
- [Usage Instructions](#usage-instructions)
- [Contributing](#contributing)
- [License](#license)

## Architecture

The Quiz Game follows the MVVM (Model-View-ViewModel) architectural pattern. It separates the concerns of data management, UI rendering, and user interactions. The project utilizes the following components and libraries:

- Jetpack Compose: A modern toolkit for building native UIs.
- Hilt: A dependency injection library for Android.
- LiveData: A data holder class from the Android Architecture Components for observing data changes.
- ViewModel: A class that is responsible for preparing and managing the data for the UI.
- Navigation: A component that handles navigation and fragment transactions.

## Functionality

The Quiz Game has the following features:

- Quiz Questions: The app displays a series of quiz questions with multiple options.
- Answer Selection: Users can select an answer for each question.
- Timer: The app displays a countdown timer for each question.
- Score Tracking: The app keeps track of the user's score based on correct answers.
- Feedback: Users receive feedback on their answers after selecting an option.
- Quiz Completion: Once all questions are answered, the app displays the final score.

## Usage Instructions

To build and run the Quiz Game project locally, follow these steps:

1. Clone the repository: git clone https://github.com/fruzelee/trivia-quiz-game-android.git

2. Open the project in Android Studio.

3. Build the project using the Gradle build task.

4. Run the app on an emulator or a physical device.

## Contributing

Contributions to the Quiz Game project are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request. Make sure to follow the project's coding style and guidelines.

## License

All rights reserved
copyright @ Fazle Rabbi
fazlerabbicse@gmail.com