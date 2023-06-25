package io.itch.fr.quizgame.feature_end.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EndPageViewModel @Inject constructor() : ViewModel() {

    var totalScore: Int = 0
        private set

    fun onPlayAgainClicked() {
        // Handle play again action
    }

    fun onViewHistoryClicked() {
        // Handle view history action
    }
}
