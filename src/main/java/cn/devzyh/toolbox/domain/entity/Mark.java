package cn.devzyh.toolbox.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 网址收藏表
 */
@Data
@TableName("MARK")
public class Mark implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 地址
     */
    private String url;

    /**
     * 来源
     */
    private String source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联标签
     */
    @TableField(exist = false)
    private List<Long> tags;

}
