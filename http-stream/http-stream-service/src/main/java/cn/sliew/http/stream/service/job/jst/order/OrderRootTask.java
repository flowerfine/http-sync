package cn.sliew.http.stream.service.job.jst.order;

import cn.sliew.http.stream.dao.mapper.jst.JstOrderMapper;
import cn.sliew.http.stream.remote.jst.JstRemoteService;
import cn.sliew.http.stream.service.job.jst.JstIncrementalRootTask;
import cn.sliew.http.stream.service.job.jst.JstIncrementalSubTask;

import java.util.Date;

class OrderRootTask extends JstIncrementalRootTask {

    private final JstRemoteService jstRemoteService;
    private final JstOrderMapper jstOrderMapper;

    public OrderRootTask(JstRemoteService jstRemoteService, JstOrderMapper jstOrderMapper) {
        this.jstRemoteService = jstRemoteService;
        this.jstOrderMapper = jstOrderMapper;
    }

    @Override
    protected JstIncrementalSubTask buildSubTask(Long id, Date startTime, Date endTime) {
        return new OrderSubTask(id, startTime, endTime, jstRemoteService, jstOrderMapper);
    }
}
