package cn.sliew.http.workflow.temporal.workflow.dsl;

import cn.sliew.http.workflow.temporal.workflow.dsl.model.ActResult;
import cn.sliew.http.workflow.temporal.workflow.dsl.model.Customer;
import io.temporal.activity.ActivityInterface;

@ActivityInterface

public interface DslActivities {

    ActResult checkCustomerInfo(Customer customer);

    ActResult approveApplication(Customer customer);

    ActResult rejectApplication(Customer customer);

    ActResult updateApplicationInfo(Customer customer);

    ActResult invokeBankingService(Customer customer);
}
