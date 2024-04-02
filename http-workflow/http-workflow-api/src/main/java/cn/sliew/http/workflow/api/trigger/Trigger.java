package cn.sliew.http.workflow.api.trigger;

import java.util.Map;

public interface Trigger {

    String getType();

    Map<String, Object> getParams();
}
