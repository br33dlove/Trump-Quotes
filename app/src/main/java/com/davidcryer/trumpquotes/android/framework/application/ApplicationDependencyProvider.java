package com.davidcryer.trumpquotes.android.framework.application;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.models.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.models.factories.QuotesAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactoryImpl;

class ApplicationDependencyProvider {

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory());
    }

    private static ViewWrapperFactory createViewStateFactory() {
        return ViewWrapperFactoryImpl.newInstance(createPresenterFactory(), createTemplateAndroidViewModelFactory());
    }

    private static PresenterFactory createPresenterFactory() {
        return PresenterFactoryImpl.newInstance();
    }

    private static QuotesAndroidViewModelFactory createTemplateAndroidViewModelFactory() {
        return QuotesAndroidViewModelFactoryImpl.newInstance();
    }
}
