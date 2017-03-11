package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

public interface SwipeQuoteMvpViewModel extends MvpViewModel {
    GameState gameState();
    enum GameState {NOT_INITIALISED, RUNNING, FINISHED}
}
