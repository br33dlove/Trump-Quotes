package com.davidcryer.trumpquotes.platformindependent.model.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

class Interactor {
    private final TaskScheduler taskScheduler;

    Interactor(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    void executeOnMainThread(final Task task) {
        taskScheduler.executeOnMainThread(task);
    }

    void executeOnWorkerThread(final Task task) {
        taskScheduler.executeOnWorkerThread(task);
    }
}
