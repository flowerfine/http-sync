package cn.sliew.http.workflow.api.queue;

import java.time.Duration;

public interface Queue {

    void addHandler(MessageHandler handler);

    void push(Message message);

    void push(Message message, Duration delay);
}
