package com.davidcryer.trumpquotes.android.framework.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;

public interface TaskFactory<RequestValuesType, ResponseValuesType> {
    Task<RequestValuesType, ResponseValuesType> create(final RequestValuesType requestValues, final Task.Callback<ResponseValuesType> callback);
}
