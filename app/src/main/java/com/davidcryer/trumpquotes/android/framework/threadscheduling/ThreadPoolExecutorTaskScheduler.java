package com.davidcryer.trumpquotes.android.framework.threadscheduling;

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
    public void scheduleOnWorkerThread(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    @Override
    public <ResponseValuesType> void scheduleOnSuccessCallbackOnUiThread(final ResponseValuesType responseValue, final Task.Callback<ResponseValuesType> callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(responseValue);
            }
        });
    }

    @Override
    public void scheduleOnErrorCallbackOnUiThread(final Task.Callback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError();
            }
        });
    }
}
