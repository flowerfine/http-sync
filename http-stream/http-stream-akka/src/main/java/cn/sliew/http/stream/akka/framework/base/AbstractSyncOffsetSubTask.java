package cn.sliew.http.stream.akka.framework.base;

import cn.sliew.http.stream.akka.framework.SyncOffsetJobContext;
import cn.sliew.http.stream.akka.framework.SyncOffsetSubTask;

public abstract class AbstractSyncOffsetSubTask<Context extends SyncOffsetJobContext, T>
        extends AbstractSubTask<Context, T> implements SyncOffsetSubTask<Context> {

    private String startSyncOffset;

    private String endSyncOffset;

    public AbstractSyncOffsetSubTask(Long id, String startSyncOffset, String endSyncOffset) {
        super(id);
        this.startSyncOffset = startSyncOffset;
        this.endSyncOffset = endSyncOffset;
    }

    @Override
    public String getStartSyncOffset() {
        return startSyncOffset;
    }

    @Override
    public String getEndSyncOffset() {
        return endSyncOffset;
    }
}
