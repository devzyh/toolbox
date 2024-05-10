package cn.devzyh.toolbox.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置表
 */
@Data
@TableName("CONFIG")
public class Config implements Serializable {

    /**
     * 标识
     */
    @TableId(type = IdType.INPUT)
    private String code;

    /**
     * 数据
     */
    private String data;

    /**
     * 类别
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

}
