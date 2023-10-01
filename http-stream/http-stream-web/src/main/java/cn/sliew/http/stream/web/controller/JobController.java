package cn.sliew.http.stream.web.controller;

import cn.sliew.http.stream.akka.jst.order.OrderStreamJob;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@Tag(name = "任务管理", description = "任务接口")
public class JobController {

    @Autowired
    private OrderStreamJob orderStreamJob;

    @GetMapping("orderStreamJob")
    @Operation(description = "订单同步")
    public void orderStreamJob() throws Exception {
        orderStreamJob.execute();
    }
}
