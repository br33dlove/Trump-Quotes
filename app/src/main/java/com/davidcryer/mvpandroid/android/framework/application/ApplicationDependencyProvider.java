package com.davidcryer.mvpandroid.android.framework.application;

import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.mvpandroid.android.view.models.factories.TemplateAndroidViewModelFactory;
import com.davidcryer.mvpandroid.android.view.models.factories.TemplateAndroidViewModelFactoryImpl;
import com.davidcryer.mvpandroid.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.mvpandroid.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.mvpandroid.platformindependent.presenter.factories.PresenterFactory;
import com.davidcryer.mvpandroid.platformindependent.presenter.factories.PresenterFactoryImpl;

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

    private static TemplateAndroidViewModelFactory createTemplateAndroidViewModelFactory() {
        return TemplateAndroidViewModelFactoryImpl.newInstance();
    }
}
