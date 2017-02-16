package com.davidcryer.trumpquotes.android.framework.tasks.factories;

import android.os.Handler;
import android.os.Looper;

import com.davidcryer.trumpquotes.android.framework.tasks.TaskScheduler;
import com.davidcryer.trumpquotes.android.framework.tasks.ThreadPoolExecutorTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTaskSchedulerFactoryImpl implements ThreadPoolExecutorTaskSchedulerFactory {

    @Override
    public TaskScheduler create(ThreadPoolExecutor threadPoolExecutor) {
        return new ThreadPoolExecutorTaskScheduler(threadPoolExecutor, new Handler(Looper.getMainLooper()));
    }
}
