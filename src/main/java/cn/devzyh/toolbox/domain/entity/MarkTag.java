package cn.devzyh.toolbox.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 网址收藏和标签关系表
 */
@Data
@TableName("MARK_TAG")
public class MarkTag implements Serializable {

    /**
     * 收藏编号
     */
    @TableId(type = IdType.NONE)
    private Long markId;

    /**
     * 标签编号
     */
    private Long tagId;

}
