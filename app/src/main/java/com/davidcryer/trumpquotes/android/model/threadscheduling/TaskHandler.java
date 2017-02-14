package com.davidcryer.trumpquotes.android.model.threadscheduling;

public interface TaskHandler {
    <RequestValuesType, ResponseValueType> void executeTask(
            final TaskFactory<RequestValuesType, ResponseValueType> taskFactory,
            final RequestValuesType requestValuesType,
            final Task.Callback<ResponseValueType> callback
    );
}
