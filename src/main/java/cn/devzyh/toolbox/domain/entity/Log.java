package cn.devzyh.toolbox.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志表
 */
@Data
@TableName("LOG")
public class Log implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 操作时间
     */
    private LocalDateTime time;

}
