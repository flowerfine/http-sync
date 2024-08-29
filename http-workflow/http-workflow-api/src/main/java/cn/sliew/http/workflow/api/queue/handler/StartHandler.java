package cn.sliew.http.workflow.api.queue.handler;

import cn.sliew.http.workflow.api.queue.Message;
import cn.sliew.http.workflow.api.queue.MessageHandler;
import cn.sliew.http.workflow.api.queue.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartHandler implements MessageHandler {

    @Override
    public Class getMessageType() {
        return StartHandler.class;
    }

    @Override
    public Queue getQueue() {
        return null;
    }

    @Override
    public void handle(Message message) {
        log.info("start execute");
    }
}
