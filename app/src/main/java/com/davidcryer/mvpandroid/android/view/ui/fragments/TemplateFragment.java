package com.davidcryer.mvpandroid.android.view.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.davidcryer.mvpandroid.R;
import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewUnbindType;
import com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.mvpandroid.android.view.ui.TemplateAndroidView;

public class TemplateFragment extends ViewBindingFragment<TemplateAndroidView.EventsListener> implements TemplateAndroidView {

    public static TemplateFragment newInstance() {
        return new TemplateFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_template, container, false);
        initialiseViewReferences(view);
        return view;
    }

    private void initialiseViewReferences(final View root) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.template_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void someScreenChange() {
        android.util.Log.v(TemplateFragment.class.getSimpleName(), "someScreenChange");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        android.util.Log.v(TemplateFragment.class.getSimpleName(), "onSaveInstanceState");
        eventsListener.onSaveInstance(outState);
    }

    @Override
    protected TemplateAndroidView.EventsListener bind(ViewWrapperRepository viewWrapperRepository, final Bundle savedState) {
        android.util.Log.v(TemplateFragment.class.getSimpleName(), "bind");
        return viewWrapperRepository.bind(this, savedState);
    }

    @Override
    protected void unbind(ViewWrapperRepository viewWrapperRepository, ViewUnbindType unbindType) {
        android.util.Log.v(TemplateFragment.class.getSimpleName(), "unbind");
        viewWrapperRepository.unbind(this, unbindType);
    }

    @Override
    public Activity activity() {
        return getActivity();
    }
}
