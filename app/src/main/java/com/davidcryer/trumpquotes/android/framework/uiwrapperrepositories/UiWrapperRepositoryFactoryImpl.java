package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory<UiWrapperRepository> {
    private final UiWrapperFactory uiWrapperFactory;

    private UiWrapperRepositoryFactoryImpl(final UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public static UiWrapperRepositoryFactory<UiWrapperRepository> newInstance(final UiWrapperFactory uiWrapperFactory) {
        return new UiWrapperRepositoryFactoryImpl(uiWrapperFactory);
    }

    @Override
    @NonNull
    public UiWrapperRepository create() {
        return new UiWrapperRepositoryImpl(uiWrapperFactory);
    }
}
