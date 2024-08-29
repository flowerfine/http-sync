package cn.sliew.http.workflow.api.instance;

public enum TaskStatus {

    BUFFERED(false, false),
    PENDING(false, false),
    RUNNING(false, false),
    SUCCESS(true, false),
    FAILURE(true, false),

    PAUSED(false, true),
    SKIPPED(true, false),
    CANCELED(true, false),
    TERMINATED(true, false),
    ;

    private boolean finished;
    private boolean blocked;

    TaskStatus(boolean finished, boolean blocked) {
        this.finished = finished;
        this.blocked = blocked;
    }
}
