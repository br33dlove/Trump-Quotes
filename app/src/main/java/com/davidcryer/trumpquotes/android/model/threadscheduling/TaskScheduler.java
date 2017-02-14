package com.davidcryer.trumpquotes.android.model.threadscheduling;

public interface TaskScheduler {
    void schedule(Runnable runnable);
    <ResponseValuesType> void scheduleOnSuccessCallback(final ResponseValuesType responseValue, final Task.Callback<ResponseValuesType> callback);
    void scheduleOnErrorCallback(final Task.Callback callback);
}
