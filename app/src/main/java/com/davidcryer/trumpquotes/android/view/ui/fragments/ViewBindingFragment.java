package com.davidcryer.trumpquotes.android.view.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.davidcryer.trumpquotes.android.framework.activities.ViewWrapperRepositoryProvider;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewUnbindType;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.AndroidMvpView;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.CastHelper;

import java.util.UUID;

abstract class ViewBindingFragment<EventsListenerType extends AndroidMvpView.EventsListener> extends Fragment {
    private final static String ARG_STATE_INSTANCE_ID = "instance id";
    private ViewWrapperRepository viewWrapperRepository;
    private EventsListenerType eventsListener;
    private String instanceId;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseViewWrapperRepositoryReference();
        instanceId = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(ARG_STATE_INSTANCE_ID);
        eventsListener = bind(viewWrapperRepository, instanceId, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (eventsListener == null) {
            eventsListener = bind(viewWrapperRepository, instanceId, null);
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

    abstract EventsListenerType bind(final ViewWrapperRepository viewWrapperRepository, final String instanceId, final Bundle savedState);

    @Override
    public void onStop() {
        super.onStop();
        unbind(viewWrapperRepository, instanceId, unbindType());
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

    abstract void unbind(final ViewWrapperRepository viewWrapperRepository, final String instanceId, final ViewUnbindType unbindType);

    boolean hasEventsListener() {
        return eventsListener != null;
    }

    EventsListenerType eventsListener() {
        return eventsListener;
    }
}
