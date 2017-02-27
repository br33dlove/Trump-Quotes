package com.davidcryer.trumpquotes.platformindependent.model.framework.tasks;

public interface TaskScheduler {
    void scheduleOnWorkerThread(Runnable runnable);
    <ResponseValuesType> void scheduleOnSuccessCallbackOnUiThread(final ResponseValuesType responseValue, final Task.Callback<ResponseValuesType> callback);
    void scheduleOnErrorCallbackOnUiThread(final Task.Callback callback);
}
