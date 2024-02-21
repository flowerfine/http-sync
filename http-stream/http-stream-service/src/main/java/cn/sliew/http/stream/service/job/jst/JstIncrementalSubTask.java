package cn.sliew.http.stream.service.job.jst;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import cn.sliew.http.stream.akka.framework.base.AbstractSyncOffsetSubTask;

public abstract class JstIncrementalSubTask<Context extends JstIncrementalJobContext, Query, Result>
        extends AbstractSyncOffsetSubTask<Context, FetchResult<Query, Result>> {

    public JstIncrementalSubTask(Long id, String startSyncOffset, String endSyncOffset) {
        super(id, startSyncOffset, endSyncOffset);
    }

    @Override
    protected abstract Source<FetchResult<Query, Result>, NotUsed> fetch(Context context);

    protected abstract Result queryJst(Context context, Query query);

    @Override
    protected void persist(Context context, FetchResult<Query, Result> data) {
        persistData(context, data.getQuery(), data.getResult());
    }

    protected abstract void persistData(Context context, Query query, Result result);
}
