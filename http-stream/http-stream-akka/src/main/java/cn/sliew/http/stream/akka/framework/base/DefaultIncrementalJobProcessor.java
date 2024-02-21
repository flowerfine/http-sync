package cn.sliew.http.stream.akka.framework.base;

import cn.sliew.http.stream.akka.framework.*;

public class DefaultIncrementalJobProcessor<Context extends SyncOffsetJobContext, Root extends RootTask, Sub extends SyncOffsetSubTask>
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
