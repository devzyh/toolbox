package cn.devzyh.toolbox.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 首页链接查询
 */
@Data
public class LinkQuery implements Serializable {

    private Long page = 1L;

    private String type;

    private String key;

}
