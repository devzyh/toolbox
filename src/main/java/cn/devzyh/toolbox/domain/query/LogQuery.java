package cn.devzyh.toolbox.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统日志查询
 */
@Data
public class LogQuery implements Serializable {

    private Long page = 1L;

    private String type;

    private String time;

}
