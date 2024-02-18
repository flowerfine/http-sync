package cn.sliew.http.stream.dao.entity.job;

import cn.sliew.http.stream.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务实例
 */
@Data
@TableName("job_instance")
public class JobInstance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("job_id")
    private Long jobId;

    @TableField("authorization_id")
    private Long authorizationId;

    @TableField("sync_offset_id")
    private Long syncOffsetId;
}
