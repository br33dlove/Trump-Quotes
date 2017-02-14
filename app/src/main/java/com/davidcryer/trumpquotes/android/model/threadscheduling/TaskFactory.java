package com.davidcryer.trumpquotes.android.model.threadscheduling;

public interface TaskFactory<RequestValuesType, ResponseValueType> {
    Task<RequestValuesType, ResponseValueType> create(final RequestValuesType requestValues, final Task.Callback<ResponseValueType> callback);
}
