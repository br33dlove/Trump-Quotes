package com.davidcryer.trumpquotes.platformindependent.model.framework.tasks;

import java.util.concurrent.ThreadPoolExecutor;

public interface ThreadPoolExecutorTaskSchedulerFactory {
    TaskScheduler create(final ThreadPoolExecutor threadPoolExecutor);
}
