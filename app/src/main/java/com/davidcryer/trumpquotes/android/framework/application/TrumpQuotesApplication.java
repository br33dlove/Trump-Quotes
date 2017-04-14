package com.davidcryer.trumpquotes.android.framework.application;

import android.app.Application;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidc.uiwrapper.UiWrapperRepositoryFactoryProvider;

public class TrumpQuotesApplication extends Application implements UiWrapperRepositoryFactoryProvider {
    private UiWrapperRepositoryFactory uiWrapperRepositoryFactory;

    public TrumpQuotesApplication() {
        super();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.uiWrapperRepositoryFactory(this);
    }

    @Override
    public UiWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return uiWrapperRepositoryFactory;
    }
}
