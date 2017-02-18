package com.davidcryer.trumpquotes.android.framework.application;

import android.app.Application;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;

public class TrumpQuotesApplication extends Application implements ViewWrapperRepositoryFactoryProvider {
    private ViewWrapperRepositoryFactory viewWrapperRepositoryFactory;

    public TrumpQuotesApplication() {
        super();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        viewWrapperRepositoryFactory = ApplicationDependencyProvider.viewWrapperRepositoryFactory(this);
    }

    @Override
    public ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return viewWrapperRepositoryFactory;
    }
}
