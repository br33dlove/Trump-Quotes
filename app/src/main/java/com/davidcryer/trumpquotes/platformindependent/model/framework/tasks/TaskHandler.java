package com.davidcryer.trumpquotes.platformindependent.model.framework.tasks;

public interface TaskHandler {
    <RequestValuesType, ResponseValuesType> void executeTask(
            final Task<RequestValuesType, ResponseValuesType> task,
            final RequestValuesType requestValuesType,
            final Task.Callback<ResponseValuesType> callback
    );
}
