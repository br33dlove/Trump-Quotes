package com.davidcryer.trumpquotes.android.model.framework.tasks;

import android.os.Handler;

import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

public class AndroidThreadPoolExecutorTaskScheduler implements TaskScheduler {
    private final ThreadPoolExecutor threadPoolExecutor;
    private final Handler handler;

    public AndroidThreadPoolExecutorTaskScheduler(ThreadPoolExecutor threadPoolExecutor, Handler handler) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.handler = handler;
    }

    @Override
    public void executeOnWorkerThread(final Task task) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }

    @Override
    public void executeOnMainThread(final Task task) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }
}
