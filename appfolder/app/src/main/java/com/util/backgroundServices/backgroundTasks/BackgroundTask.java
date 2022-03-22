package com.util.backgroundServices.backgroundTasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

abstract public class BackgroundTask implements Runnable {
    private static final String LOG_TAG = "BackgroundTask";
    static public final String SUCCESS_KEY = "SUCCESS_KEY";
    static public final String MESSAGE_KEY = "MESSAGE_KEY";
    static public final String EXCEPTION_KEY = "EXCEPTION_KEY";

    /**
     * Message handler that will receive task results.
     */
    private final Handler messageHandler;

    protected BackgroundTask(Handler messageHandler) {
        this.messageHandler = messageHandler;
    }


    @Override
    public void run() {
        try {
            runTask();
            sendSuccessMessage();
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            sendExceptionMessage(ex);
        }
    }


    protected abstract void runTask() throws IOException;

    private void sendSuccessMessage() {
        Bundle msgBundle = createMessageBundle(true);
        loadBundle(msgBundle);
        sendMessage(msgBundle);
    }

    protected abstract void loadBundle(Bundle msgBundle);

    protected void sendFailedMessage(String message) {
        Bundle msgBundle = createMessageBundle(false);
        msgBundle.putString(MESSAGE_KEY, message);
        sendMessage(msgBundle);
    }

    protected void sendExceptionMessage(Exception exception) {
        Bundle msgBundle = createMessageBundle(false);
        msgBundle.putSerializable(EXCEPTION_KEY, exception);
        sendMessage(msgBundle);
    }

    @NotNull
    private Bundle createMessageBundle(boolean value) {
        Bundle msgBundle = new Bundle();
        msgBundle.putBoolean(SUCCESS_KEY, value);
        return msgBundle;
    }

    private void sendMessage(Bundle msgBundle) {
        Message msg = Message.obtain();
        msg.setData(msgBundle);
        messageHandler.sendMessage(msg);
    }
}
