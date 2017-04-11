package com.davidcryer.trumpquotes.android.model.framework.tasks;

import android.os.Handler;
import android.os.Looper;

import com.davidc.interactor.TaskScheduler;
import com.davidc.interactor.ThreadPoolExecutorTaskSchedulerFactory;

import java.util.concurrent.ThreadPoolExecutor;

public class AndroidThreadPoolExecutorTaskSchedulerFactory implements ThreadPoolExecutorTaskSchedulerFactory {

    @Override
    public TaskScheduler create(ThreadPoolExecutor threadPoolExecutor) {
        return new AndroidThreadPoolExecutorTaskScheduler(threadPoolExecutor, new Handler(Looper.getMainLooper()));
    }
}
