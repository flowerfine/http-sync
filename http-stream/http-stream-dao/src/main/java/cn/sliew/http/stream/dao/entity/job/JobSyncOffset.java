package cn.sliew.http.stream.dao.entity.job;

import cn.sliew.http.stream.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务进度位点
 */
@Data
@TableName("job_sync_offset")
public class JobSyncOffset extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("job_id")
    private Long jobId;

    @TableField("authorization_id")
    private Long authorizationId;

    @TableField("last_sync_offset")
    private String lastSyncOffset;

    @TableField("sync_offset")
    private String syncOffset;
}
