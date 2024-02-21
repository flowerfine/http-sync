package cn.sliew.http.stream.service.enums;

import lombok.Getter;

@Getter
public enum JobGroup {

    JST("jst", "聚水潭"),
    ;

    private String group;
    private String desc;

    JobGroup(String group, String desc) {
        this.group = group;
        this.desc = desc;
    }
}
