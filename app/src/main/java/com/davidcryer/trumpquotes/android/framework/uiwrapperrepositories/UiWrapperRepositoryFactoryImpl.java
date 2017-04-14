package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactory;
import com.example.davidc.uiwrapper.UiWrapperRepository;
import com.example.davidc.uiwrapper.UiWrapperRepositoryFactory;

public class UiWrapperRepositoryFactoryImpl implements UiWrapperRepositoryFactory {
    private final UiWrapperFactory uiWrapperFactory;

    private UiWrapperRepositoryFactoryImpl(final UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public static UiWrapperRepositoryFactory newInstance(final UiWrapperFactory uiWrapperFactory) {
        return new UiWrapperRepositoryFactoryImpl(uiWrapperFactory);
    }

    @Override
    public UiWrapperRepository create() {
        return new UiWrapperRepositoryImpl(uiWrapperFactory);
    }
}
