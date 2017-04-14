package com.davidcryer.trumpquotes.android.framework.application;

import android.app.Application;

import com.example.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class TrumpQuotesApplication extends Application implements ViewWrapperRepositoryFactoryProvider {
    private UiWrapperRepositoryFactory UiWrapperRepositoryFactory;

    public TrumpQuotesApplication() {
        super();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        UiWrapperRepositoryFactory = ApplicationDependencyProvider.viewWrapperRepositoryFactory(this);
    }

    @Override
    public ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return UiWrapperRepositoryFactory;
    }
}
