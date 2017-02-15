package com.davidcryer.trumpquotes.android.model.threadscheduling;

import java.lang.ref.WeakReference;

public class TaskHandlerImpl implements TaskHandler {
    private final TaskScheduler taskScheduler;

    public TaskHandlerImpl(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Override
    public <RequestValuesType, ResponseValuesType> void executeTask(
            TaskFactory<RequestValuesType, ResponseValuesType> taskFactory,
            RequestValuesType requestValuesType,
            Task.Callback<ResponseValuesType> callback
    ) {
        final Task<RequestValuesType, ResponseValuesType> task = taskFactory.create(requestValuesType, taskCallbackWrapper(callback));
        taskScheduler.scheduleOnWorkerThread(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }

    private <ResponseValuesType> TaskCallbackWrapper<ResponseValuesType> taskCallbackWrapper(final Task.Callback<ResponseValuesType> callback) {
        return new TaskCallbackWrapper<>(callback, new TaskCallbackHandler<ResponseValuesType>() {
            @Override
            public void executeOnSuccessCallback(ResponseValuesType responseValue, Task.Callback<ResponseValuesType> callback) {
                taskScheduler.scheduleOnSuccessCallbackOnUiThread(responseValue, callback);
            }

            @Override
            public void executeOfErrorCallback(Task.Callback callback) {
                taskScheduler.scheduleOnErrorCallbackOnUiThread(callback);
            }
        });
    }

    interface TaskCallbackHandler<ResponseValuesType> {
        void executeOnSuccessCallback(final ResponseValuesType responseValue, final Task.Callback<ResponseValuesType> callback);
        void executeOfErrorCallback(final Task.Callback callback);
    }

    private static class TaskCallbackWrapper<ResponseValuesType> implements Task.Callback<ResponseValuesType> {
        private final WeakReference<Task.Callback<ResponseValuesType>> callback;
        private final TaskCallbackHandler<ResponseValuesType> taskCallbackHandler;

        private TaskCallbackWrapper(final Task.Callback<ResponseValuesType> callback, final TaskCallbackHandler<ResponseValuesType> taskCallbackHandler) {
            this.callback = new WeakReference<>(callback);
            this.taskCallbackHandler = taskCallbackHandler;
        }

        @Override
        public void onSuccess(ResponseValuesType response) {
            if (callback.get() != null) {
                taskCallbackHandler.executeOnSuccessCallback(response, callback.get());
            }
        }

        @Override
        public void onError() {
            if (callback.get() != null) {
                taskCallbackHandler.executeOfErrorCallback(callback.get());
            }
        }
    }
}
