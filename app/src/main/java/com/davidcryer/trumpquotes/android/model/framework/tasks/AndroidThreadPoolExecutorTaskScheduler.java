package com.davidcryer.trumpquotes.android.model.framework.tasks;

import android.os.Handler;

import com.davidc.interactor.Task;
import com.davidc.interactor.TaskScheduler;

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
    public void executeOnCallbackThread(final Task task) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }
}
