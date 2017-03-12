package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

public interface QuizViewModel extends MvpViewModel {
    GameState gameState();
    enum GameState {NOT_INITIALISED, INITIALISED, FINISHED}
}
