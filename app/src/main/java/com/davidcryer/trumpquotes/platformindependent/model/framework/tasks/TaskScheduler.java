package com.davidcryer.trumpquotes.platformindependent.model.framework.tasks;

public interface TaskScheduler {
    void executeOnWorkerThread(final Task task);
    void executeOnMainThread(final Task task);
}
