package cn.sliew.http.workflow.api.queue;

public interface MessageHandler {

    Class getMessageType();

    Queue getQueue();

    default void invoke(Message message) {
        if (getMessageType().isAssignableFrom(message.getClass()) == false) {
            throw new IllegalArgumentException("Unsupported message type " + message.getClass().getSimpleName());
        }
        handle(message);
    }

    void handle(Message message);
}
