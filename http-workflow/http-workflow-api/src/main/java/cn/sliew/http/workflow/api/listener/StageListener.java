package cn.sliew.http.workflow.api.listener;

import cn.sliew.http.workflow.api.instance.StageInstance;
import cn.sliew.http.workflow.api.instance.TaskInstance;

public interface StageListener {

    default void beforeStage(StageInstance stage) {
        // do nothing
    }

    default void beforeTask(StageInstance stage, TaskInstance task) {
        // do nothing
    }

    default void afterTask(StageInstance stage, TaskInstance task) {
        // do nothing
    }

    default void afterStage(StageInstance stage) {
        // do nothing
    }
}
