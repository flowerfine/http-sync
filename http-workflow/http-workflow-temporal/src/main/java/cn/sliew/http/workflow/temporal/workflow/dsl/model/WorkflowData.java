package cn.sliew.http.workflow.temporal.workflow.dsl.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WorkflowData {

    private static final ObjectMapper mapper = new ObjectMapper();
    private ObjectNode value;

    public WorkflowData() {
        value = mapper.createObjectNode();
    }

    public WorkflowData(String data) {
        try {
            if (data == null || data.trim().length() < 1) {
                value = mapper.createObjectNode();
            } else {
                value = (ObjectNode) mapper.readTree(data);
            }
            // value.putArray("results");
        } catch (JacksonException e) {
            throw new IllegalArgumentException("Invalid workflow data input: " + e.getMessage());
        }
    }

    public Customer getCustomer() {
        try {
            return mapper.readValue(value.get("customer").toPrettyString(), Customer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObjectNode getValue() {
        return value;
    }

    public void setValue(ObjectNode value) {
        this.value = value;
    }

    public void addResults(Object result) {
        ((ArrayNode) this.value.get("results")).add(mapper.valueToTree(result));
    }

    public String valueToString() {
        return value.toPrettyString();
    }
}
