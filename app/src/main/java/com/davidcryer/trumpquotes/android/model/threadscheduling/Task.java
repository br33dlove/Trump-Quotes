package com.davidcryer.trumpquotes.android.model.threadscheduling;

import java.lang.ref.WeakReference;

public abstract class Task<RequestValuesType, ResponseValueType> {
    private final RequestValuesType requestValues;
    private final WeakReference<Callback<ResponseValueType>> callback;

    public Task(RequestValuesType requestValues, Callback<ResponseValueType> callback) {
        this.requestValues = requestValues;
        this.callback = new WeakReference<>(callback);
    }

    void execute() {
        doTask(requestValues);
    }

    protected abstract void doTask(final RequestValuesType requestValues);

    protected void onSuccess(final ResponseValueType responseValue) {
        if (callback.get() != null) {
            callback.get().onSuccess(responseValue);
        }
    }

    protected void onError() {
        if (callback.get() != null) {
            callback.get().onError();
        }
    }

    public interface Callback<ResponseValueType> {
        void onSuccess(ResponseValueType response);
        void onError();
    }
}
