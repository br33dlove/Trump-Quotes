package com.davidcryer.trumpquotes.android.framework.application;

import android.app.Application;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;

public class TrumpQuotesApplication extends Application implements ViewWrapperRepositoryFactoryProvider {
    private final ViewWrapperRepositoryFactory viewWrapperRepositoryFactory;

    public TrumpQuotesApplication() {
        super();
        viewWrapperRepositoryFactory = ApplicationDependencyProvider.viewWrapperRepositoryFactory();
    }

    @Override
    public ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return viewWrapperRepositoryFactory;
    }
}
