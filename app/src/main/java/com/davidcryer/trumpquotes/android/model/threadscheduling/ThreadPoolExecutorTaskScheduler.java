package com.davidcryer.trumpquotes.android.model.threadscheduling;

import android.os.Handler;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTaskScheduler implements TaskScheduler {
    private final ThreadPoolExecutor threadPoolExecutor;
    private final Handler handler;

    public ThreadPoolExecutorTaskScheduler(ThreadPoolExecutor threadPoolExecutor, Handler handler) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.handler = handler;
    }

    @Override
    public void schedule(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    @Override
    public <ResponseValueType> void scheduleOnSuccessCallback(final ResponseValueType responseValue, final Task.Callback<ResponseValueType> callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(responseValue);
            }
        });
    }

    @Override
    public void scheduleOnErrorCallback(final Task.Callback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError();
            }
        });
    }
}
