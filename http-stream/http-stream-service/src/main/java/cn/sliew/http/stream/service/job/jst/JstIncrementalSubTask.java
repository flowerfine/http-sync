package cn.sliew.http.stream.service.job.jst;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import cn.sliew.http.stream.akka.framework.base.AbstractSubTask;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class JstIncrementalSubTask<Context extends JstIncrementalJobContext, Query, Result>
        extends AbstractSubTask<Context, FetchResult<Query, Result>> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Date endTime;

    public JstIncrementalSubTask(Long id, Date startTime, Date endTime) {
        super(id);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
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
