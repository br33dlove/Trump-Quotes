package com.davidcryer.trumpquotes.android.model.framework.tasks;

import android.os.Handler;
import android.os.Looper;

import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;

import java.util.concurrent.ThreadPoolExecutor;

public class AndroidThreadPoolExecutorTaskSchedulerFactory implements ThreadPoolExecutorTaskSchedulerFactory {

    @Override
    public TaskScheduler create(ThreadPoolExecutor threadPoolExecutor) {
        return new AndroidThreadPoolExecutorTaskScheduler(threadPoolExecutor, new Handler(Looper.getMainLooper()));
    }
}
