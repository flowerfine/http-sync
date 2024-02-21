package cn.sliew.http.stream.service.enums;

public enum JstJob {

    ORDERS_SINGLE_QUERY(JobGroup.JST, JstMethodEnum.ORDERS_SINGLE_QUERY, JstJobType.NORMAL, "订单查询"),
    ORDERS_SINGLE_QUERY_BACKSTRACK(JobGroup.JST, JstMethodEnum.ORDERS_SINGLE_QUERY, JstJobType.BACKTRACK, "订单查询-回溯"),
    ORDERS_SINGLE_QUERY_TS(JobGroup.JST, JstMethodEnum.ORDERS_SINGLE_QUERY, JstJobType.TS, "订单查询-TS"),
    ;

    private JobGroup group;
    private JstMethodEnum method;
    private JstJobType type;
    private String desc;

    JstJob(JobGroup group, JstMethodEnum method, JstJobType type, String desc) {
        this.group = group;
        this.method = method;
        this.type = type;
        this.desc = desc;
    }

    public String getGroup() {
        return group.getGroup();
    }

    public String getJob() {
        return method.getMethod();
    }

    public String getType() {
        return type.getType();
    }
}
