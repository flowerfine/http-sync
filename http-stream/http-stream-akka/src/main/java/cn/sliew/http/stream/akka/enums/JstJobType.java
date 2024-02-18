package cn.sliew.http.stream.akka.enums;

import lombok.Getter;

@Getter
public enum JstJobType {

    NORMAL("normal", "普通任务"),
    BACKTRACK("backtrack", "回溯任务"),
    FAR_BACKTRACK("far_backtrack", "回溯任务"),
    RECENT_BACKTRACK("recent_backtrack", "回溯任务"),
    PAGE("page", "分页查询"),
    TS("ts", "TS查询"),
    ARCHIVE("archive", "归档数据"),
    ;

    private String type;
    private String desc;

    JstJobType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
