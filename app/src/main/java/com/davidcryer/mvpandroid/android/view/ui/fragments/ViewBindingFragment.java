package com.davidcryer.mvpandroid.android.view.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.davidcryer.mvpandroid.android.framework.activities.ViewWrapperRepositoryProvider;
import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewUnbindType;
import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.mvpandroid.android.view.ui.AndroidMvpView;
import com.davidcryer.mvpandroid.platformindependent.javahelpers.CastHelper;

abstract class ViewBindingFragment<EventsListenerType extends AndroidMvpView.EventsListener> extends Fragment {
    private ViewWrapperRepository viewWrapperRepository;
    EventsListenerType eventsListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        android.util.Log.d(ViewBindingFragment.class.getSimpleName(), "onViewCreated");
        initialiseViewWrapperRepositoryReference();
        eventsListener = bind(viewWrapperRepository, savedInstanceState);
    }



    @Override
    public void onStart() {
        super.onStart();
        if (eventsListener == null) {
            android.util.Log.d(ViewBindingFragment.class.getSimpleName(), "onStart, events listener is null");
            eventsListener = bind(viewWrapperRepository, null);
        }
    }

    private void initialiseViewWrapperRepositoryReference() {
        viewWrapperRepository = getViewWrapperRepository();
    }

    private ViewWrapperRepository getViewWrapperRepository() {
        return getViewWrapperRepositoryProvider().viewWrapperRepository();
    }

    private ViewWrapperRepositoryProvider getViewWrapperRepositoryProvider() {
        return CastHelper.riskyCastToInterface(getActivity(), ViewWrapperRepositoryProvider.class);
    }

    abstract EventsListenerType bind(final ViewWrapperRepository viewWrapperRepository, final Bundle savedState);

    @Override
    public void onStop() {
        super.onStop();
        android.util.Log.d(ViewBindingFragment.class.getSimpleName(), "onStop");
        unbind(viewWrapperRepository, unbindType());
        eventsListener = null;
    }

    private ViewUnbindType unbindType() {
        if (getActivity().isChangingConfigurations()) {
            return ViewUnbindType.CONFIG_CHANGE;
        }
        if (getActivity().isFinishing()) {
            return ViewUnbindType.FINISH;
        }
        return ViewUnbindType.NON_CONFIG_CHANGE;
    }

    abstract void unbind(final ViewWrapperRepository viewWrapperRepository, final ViewUnbindType unbindType);
}
