package cn.sliew.http.stream.dao.entity.job;

import cn.sliew.http.stream.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务信息
 */
@Data
@TableName("job_info")
public class JobInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("group_id")
    private Long groupId;

    @TableField("job")
    private String job;

    @TableField("sub_job")
    private String subJob;
}
