package com.davidcryer.trumpquotes.android.framework.threadscheduling;

public interface TaskFactory<RequestValuesType, ResponseValuesType> {
    Task<RequestValuesType, ResponseValuesType> create(final RequestValuesType requestValues, final Task.Callback<ResponseValuesType> callback);
}
