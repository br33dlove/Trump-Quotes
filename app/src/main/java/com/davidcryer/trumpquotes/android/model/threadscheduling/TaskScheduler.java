package com.davidcryer.trumpquotes.android.model.threadscheduling;

public interface TaskScheduler {
    void schedule(Runnable runnable);
    <ResponseValueType> void scheduleOnSuccessCallback(final ResponseValueType responseValue, final Task.Callback<ResponseValueType> callback);
    void scheduleOnErrorCallback(final Task.Callback callback);
}
