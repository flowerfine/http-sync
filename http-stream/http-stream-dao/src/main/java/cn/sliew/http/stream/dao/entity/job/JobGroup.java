package cn.sliew.http.stream.dao.entity.job;

import cn.sliew.http.stream.dao.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 任务分组
 */
@Data
@TableName("job_group")
public class JobGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("`group`")
    private String group;
}
