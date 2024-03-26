package cn.sliew.http.workflow.temporal.workflow.dsl.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String name;
    private int age;
    private List<Integer> transactions;
}
