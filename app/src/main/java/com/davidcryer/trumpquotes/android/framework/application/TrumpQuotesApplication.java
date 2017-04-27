package com.davidcryer.trumpquotes.android.framework.application;

import android.app.Application;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepository;

public class TrumpQuotesApplication extends Application implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private UiWrapperRepositoryFactory<UiWrapperRepository> uiWrapperRepositoryFactory;

    public TrumpQuotesApplication() {
        super();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        uiWrapperRepositoryFactory = ApplicationDependencyProvider.uiWrapperRepositoryFactory(this);
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return uiWrapperRepositoryFactory.create();
    }
}
