package cn.devzyh.toolbox.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章收藏查询
 */
@Data
public class MarkQuery implements Serializable {

    private Long page = 1L;

    private String source;

    private String key;

}
