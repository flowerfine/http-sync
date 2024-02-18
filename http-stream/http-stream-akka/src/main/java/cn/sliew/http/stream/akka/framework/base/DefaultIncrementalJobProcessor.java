package cn.sliew.http.stream.akka.framework.base;

import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SubTask;
import cn.sliew.http.stream.akka.framework.SyncOffsetJobContext;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;

public class DefaultIncrementalJobProcessor<Context extends SyncOffsetJobContext, Root extends RootTask, Sub extends SubTask>
        extends DefaultJobProcessor<Context, Root, Sub> {

    public DefaultIncrementalJobProcessor(Context context) {
        super(context);
    }

    @Override
    protected void handleSubTaskResult(Sub subTask) {
        SyncOffsetManager syncOffsetManager = context.getSyncOffsetManager();
        syncOffsetManager.updateSyncOffset(context, subTask);
    }
}
