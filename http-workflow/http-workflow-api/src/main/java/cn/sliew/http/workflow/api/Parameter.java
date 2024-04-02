package cn.sliew.http.workflow.api;

import lombok.Data;

@Data
public class Parameter {

    private String name;
    private String description;
    private Object defaults;
    private boolean required;
}
