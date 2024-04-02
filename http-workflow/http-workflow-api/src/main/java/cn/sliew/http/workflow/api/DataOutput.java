package cn.sliew.http.workflow.api;

import lombok.Data;

import java.util.Map;

@Data
public class DataOutput {

    private Map<String, Object> items;
}
