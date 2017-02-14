package com.davidcryer.trumpquotes.android.model.threadscheduling;

import java.lang.ref.WeakReference;

public class TaskHandlerImpl implements TaskHandler {
    private final TaskScheduler taskScheduler;

    public TaskHandlerImpl(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Override
    public <RequestValuesType, ResponseValueType> void executeTask(
            TaskFactory<RequestValuesType, ResponseValueType> taskFactory,
            RequestValuesType requestValuesType,
            Task.Callback<ResponseValueType> callback
    ) {
        final Task<RequestValuesType, ResponseValueType> task = taskFactory.create(requestValuesType, taskCallbackWrapper(callback));
        taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });
    }

    private <ResponseValueType> TaskCallbackWrapper<ResponseValueType> taskCallbackWrapper(final Task.Callback<ResponseValueType> callback) {
        return new TaskCallbackWrapper<>(callback, new TaskCallbackHandler<ResponseValueType>() {
            @Override
            public void executeOnSuccessCallback(ResponseValueType responseValue, Task.Callback<ResponseValueType> callback) {
                taskScheduler.scheduleOnSuccessCallback(responseValue, callback);
            }

            @Override
            public void executeOfErrorCallback(Task.Callback callback) {
                taskScheduler.scheduleOnErrorCallback(callback);
            }
        });
    }

    interface TaskCallbackHandler<ResponseValueType> {
        void executeOnSuccessCallback(final ResponseValueType responseValue, final Task.Callback<ResponseValueType> callback);
        void executeOfErrorCallback(final Task.Callback callback);
    }

    private static class TaskCallbackWrapper<ResponseValueType> implements Task.Callback<ResponseValueType> {
        private final WeakReference<Task.Callback<ResponseValueType>> callback;
        private final TaskCallbackHandler taskCallbackHandler;

        private TaskCallbackWrapper(final Task.Callback<ResponseValueType> callback, final TaskCallbackHandler taskCallbackHandler) {
            this.callback = new WeakReference<>(callback);
            this.taskCallbackHandler = taskCallbackHandler;
        }

        @Override
        public void onSuccess(ResponseValueType response) {
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
