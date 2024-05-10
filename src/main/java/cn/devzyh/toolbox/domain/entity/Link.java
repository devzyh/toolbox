package cn.devzyh.toolbox.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页链接表
 */
@Data
@TableName("LINK")
public class Link implements Serializable {

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
     * 所属类型
     */
    private String type;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 打开方式
     */
    private String open;

    /**
     * 备注
     */
    private String remark;

}
