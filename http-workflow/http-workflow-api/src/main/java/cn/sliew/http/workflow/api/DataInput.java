package cn.sliew.http.workflow.api;

import lombok.Data;

import java.util.Map;

@Data
public class DataInput {

    private Map<String, Parameter> items;
}
