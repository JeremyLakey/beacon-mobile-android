package com.util.backgroundServices.backgroundTasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public abstract class BackgroundTaskHandler<T extends BackgroundTaskHandler.TaskObserver> extends Handler {

    public interface TaskObserver {
        void handleFailure(String message);
        void handleSuccess(Bundle bundle);
    }

    private final T observer;

    public BackgroundTaskHandler(T observer) {
        this.observer = observer;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        boolean success = msg.getData().getBoolean(BackgroundTask.SUCCESS_KEY);
        if (success) {
            handleSuccessMessage(observer, msg.getData());
        } else if (msg.getData().containsKey(BackgroundTask.MESSAGE_KEY)) {
            String message = getFailedMessagePrefix() + ": " + msg.getData().getString(BackgroundTask.MESSAGE_KEY);
            observer.handleFailure(message);
        } else if (msg.getData().containsKey(BackgroundTask.EXCEPTION_KEY)) {
            Exception ex = (Exception) msg.getData().getSerializable(BackgroundTask.EXCEPTION_KEY);
            String message = getFailedMessagePrefix() + " because of exception: " + ex.getMessage();
            observer.handleFailure(message);
        }
    }

    protected abstract String getFailedMessagePrefix();

    protected abstract void handleSuccessMessage(T observer, Bundle data);
}
