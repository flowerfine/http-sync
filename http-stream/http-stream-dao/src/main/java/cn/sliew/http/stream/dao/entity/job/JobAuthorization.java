package cn.sliew.http.stream.dao.entity.job;

import cn.sliew.http.stream.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务授权
 */
@Data
@TableName("job_authorization")
public class JobAuthorization extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("`account`")
    private String account;

    @TableField("sub_account")
    private String subAccount;

    @TableField("authorization")
    private String authorization;
}
