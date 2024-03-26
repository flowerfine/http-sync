package cn.sliew.http.workflow.temporal.workflow.dsl;

import cn.sliew.http.workflow.temporal.workflow.dsl.model.ActResult;
import cn.sliew.http.workflow.temporal.workflow.dsl.model.Customer;
import io.temporal.activity.Activity;

public class DslActivitiesImpl implements DslActivities {

    @Override
    public ActResult checkCustomerInfo(Customer customer) {
        try {
            return new ActResult(Activity.getExecutionContext().getInfo().getActivityType(), "invoked");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ActResult updateApplicationInfo(Customer customer) {
        try {
            return new ActResult(Activity.getExecutionContext().getInfo().getActivityType(), "invoked");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ActResult approveApplication(Customer customer) {
        try {
            return new ActResult("decision", "APPROVED");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ActResult rejectApplication(Customer customer) {
        try {
            return new ActResult("decision-" + customer.getName(), "DENIED");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ActResult invokeBankingService(Customer customer) {
        try {
            return new ActResult(Activity.getExecutionContext().getInfo().getActivityType(), "invoked");
        } catch (Exception e) {
            return null;
        }
    }
}
