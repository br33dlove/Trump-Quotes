package com.davidcryer.trumpquotes.android.framework.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.factories.TaskFactory;

public interface TaskHandler {
    <RequestValuesType, ResponseValuesType> void executeTask(
            final TaskFactory<RequestValuesType, ResponseValuesType> taskFactory,
            final RequestValuesType requestValuesType,
            final Task.Callback<ResponseValuesType> callback
    );
}
