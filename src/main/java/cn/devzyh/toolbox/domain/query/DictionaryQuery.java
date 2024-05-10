package cn.devzyh.toolbox.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据字典查询
 */
@Data
public class DictionaryQuery implements Serializable {

    private Long page = 1L;

    private String type;

    private String code;

    private String data;

}
