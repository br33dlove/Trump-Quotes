package com.davidcryer.trumpquotes.android.model.tasks;

import android.os.Handler;
import android.os.Looper;

import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTaskSchedulerFactoryImpl implements ThreadPoolExecutorTaskSchedulerFactory {

    @Override
    public TaskScheduler create(ThreadPoolExecutor threadPoolExecutor) {
        return new ThreadPoolExecutorTaskScheduler(threadPoolExecutor, new Handler(Looper.getMainLooper()));
    }
}
