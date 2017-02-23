package com.davidcryer.trumpquotes.android.framework.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.TaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

public interface ThreadPoolExecutorTaskSchedulerFactory {
    TaskScheduler create(final ThreadPoolExecutor threadPoolExecutor);
}
