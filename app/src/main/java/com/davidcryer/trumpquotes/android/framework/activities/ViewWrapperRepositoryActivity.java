package com.davidcryer.trumpquotes.android.framework.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.davidcryer.trumpquotes.android.framework.application.ViewWrapperRepositoryFactoryProvider;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.helpers.FragmentManagerHelper;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.CastHelper;

class ViewWrapperRepositoryActivity extends AppCompatActivity implements ViewWrapperRepositoryProvider {
    private final static String FRAGMENT_TAG_VIEW_WRAPPER_REPOSITORY = "view wrapper repository";
    private ViewWrapperRepositoryFragment viewWrapperRepositoryFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseViewWrapperRepositoryFragment();
    }

    private void initialiseViewWrapperRepositoryFragment() {
        android.util.Log.v(ViewWrapperRepositoryActivity.class.getSimpleName(), "initialiseViewWrapperRepositoryFragment");
        viewWrapperRepositoryFragment = (ViewWrapperRepositoryFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_VIEW_WRAPPER_REPOSITORY);
        if (viewWrapperRepositoryFragment == null) {
            viewWrapperRepositoryFragment = ViewWrapperRepositoryFragment.newInstance();
            FragmentManagerHelper.addFragment(getSupportFragmentManager(), viewWrapperRepositoryFragment, FRAGMENT_TAG_VIEW_WRAPPER_REPOSITORY);
        }
    }

    @Override
    public ViewWrapperRepository viewWrapperRepository() {
        return viewWrapperRepositoryFragment.viewWrapperRepository();
    }

    public static class ViewWrapperRepositoryFragment extends Fragment {
        private ViewWrapperRepository viewWrapperRepository;

        public static ViewWrapperRepositoryFragment newInstance() {
            return new ViewWrapperRepositoryFragment();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            initialiseViewWrapperRepository();
        }

        private void initialiseViewWrapperRepository() {
            android.util.Log.v(ViewWrapperRepositoryActivity.class.getSimpleName(), "initialiseViewWrapperRepository");
            viewWrapperRepository = viewWrapperRepositoryFactory().create();
        }

        private ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
            return viewWrapperRepositoryFactoryProvider().viewWrapperRepositoryFactory();
        }

        private ViewWrapperRepositoryFactoryProvider viewWrapperRepositoryFactoryProvider() {
            return CastHelper.riskyCastToInterface(getActivity().getApplication(), ViewWrapperRepositoryFactoryProvider.class);
        }

        ViewWrapperRepository viewWrapperRepository() {
            return viewWrapperRepository;
        }
    }
}
