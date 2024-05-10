package cn.devzyh.toolbox.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 地址数据
 */
@Data
public class UrlModel implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 地址
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签
     */
    private List<String> tags;

}
