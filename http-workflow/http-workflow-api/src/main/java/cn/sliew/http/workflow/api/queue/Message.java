package cn.sliew.http.workflow.api.queue;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "kind")
public abstract class Message {

    private List<Attribute> attributes;

    public void setAttribute(Attribute attribute) {

    }

    public Attribute getAttribute() {
        return null;
    }

    interface Attribute {

    }
}
