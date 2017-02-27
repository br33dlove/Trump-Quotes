package com.davidcryer.trumpquotes.platformindependent.model.framework.tasks;

public abstract class Task<RequestValuesType, ResponseValuesType> {

    void execute(final RequestValuesType requestValues, final Callback<ResponseValuesType> callback) {
        doTask(requestValues, callback);
    }

    protected abstract void doTask(final RequestValuesType requestValues, final Callback<ResponseValuesType> callback);

    protected void onSuccess(final ResponseValuesType responseValue, final Callback<ResponseValuesType> callback) {
        if (callback != null) {
            callback.onSuccess(responseValue);
        }
    }

    protected void onError(final Callback<ResponseValuesType> callback) {
        if (callback != null) {
            callback.onError();
        }
    }

    public interface Callback<ResponseValuesType> {
        void onSuccess(ResponseValuesType response);
        void onError();
    }
}
