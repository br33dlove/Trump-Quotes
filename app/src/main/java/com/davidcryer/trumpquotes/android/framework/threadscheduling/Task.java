package com.davidcryer.trumpquotes.android.framework.threadscheduling;

import java.lang.ref.WeakReference;

public abstract class Task<RequestValuesType, ResponseValuesType> {
    private final RequestValuesType requestValues;
    private final WeakReference<Callback<ResponseValuesType>> callback;

    public Task(RequestValuesType requestValues, Callback<ResponseValuesType> callback) {
        this.requestValues = requestValues;
        this.callback = new WeakReference<>(callback);
    }

    void execute() {
        doTask(requestValues);
    }

    protected abstract void doTask(final RequestValuesType requestValues);

    protected void onSuccess(final ResponseValuesType responseValue) {
        if (callback.get() != null) {
            callback.get().onSuccess(responseValue);
        }
    }

    protected void onError() {
        if (callback.get() != null) {
            callback.get().onError();
        }
    }

    public interface Callback<ResponseValuesType> {
        void onSuccess(ResponseValuesType response);
        void onError();
    }
}
