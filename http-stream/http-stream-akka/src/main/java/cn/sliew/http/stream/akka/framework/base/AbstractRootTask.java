package cn.sliew.http.stream.akka.framework.base;

import akka.japi.Pair;
import cn.sliew.http.stream.akka.framework.*;
import cn.sliew.http.stream.akka.util.GradientUtil;
import cn.sliew.http.stream.akka.util.SyncOffsetUtil;
import cn.sliew.http.stream.common.util.DateUtil;
import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractRootTask<Context extends SyncOffsetJobContext, Sub extends SubTask>
        implements RootTask<Context, Sub> {

    @Override
    public List<Sub> split(Context context) {
        SyncOffsetManager syncOffsetManager = context.getSyncOffsetManager();

        JobSyncOffset syncOffset = syncOffsetManager.getSyncOffset(context);
        Date startTime = DateUtil.parseDateTime(syncOffset.getSyncOffset());
        Date endTime = DateUtil.parseDateTime(context.getFinalSyncOffset());
        List<Pair<Date, Date>> pairs = SyncOffsetUtil.trySplit(startTime, endTime, getGradients(), getBatchSize(context));

        List<Sub> subTasks = new ArrayList<>(pairs.size());
        for (int i = 0; i < pairs.size(); i++) {
            Pair<Date, Date> pair = pairs.get(i);
            subTasks.add(buildSubTask(Long.valueOf(i), DateUtil.formatDateTime(pair.first()), DateUtil.formatDateTime(pair.second())));
        }
        return subTasks;
    }

    private int getBatchSize(Context context) {
        if (context instanceof Parallel) {
            Parallel parallel = (Parallel) context;
            return parallel.getSubTaskBatchSize();
        }
        return 1;
    }

    protected List<Duration> getGradients() {
        return GradientUtil.getDefaultGradients();
    }

    protected abstract Sub buildSubTask(Long id, String startSyncOffset, String endSyncOffset);
}
