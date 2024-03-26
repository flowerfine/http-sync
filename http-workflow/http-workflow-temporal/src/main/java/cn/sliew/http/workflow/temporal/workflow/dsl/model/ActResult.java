package cn.sliew.http.workflow.temporal.workflow.dsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActResult {

    private String type;
    private String result;
}
