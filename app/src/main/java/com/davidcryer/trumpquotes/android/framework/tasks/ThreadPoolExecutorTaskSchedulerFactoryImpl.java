package com.davidcryer.trumpquotes.android.framework.tasks;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTaskSchedulerFactoryImpl implements ThreadPoolExecutorTaskSchedulerFactory {

    @Override
    public TaskScheduler create(ThreadPoolExecutor threadPoolExecutor) {
        return new ThreadPoolExecutorTaskScheduler(threadPoolExecutor, new Handler(Looper.getMainLooper()));
    }
}
