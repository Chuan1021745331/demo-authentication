package com.chuan.authority.param;

import lombok.Data;

/**
 * <p>
 *  部门操作参数
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/29 23:01
 */
@Data
public class SysDeptParam {

    private Integer id=0;

    private String name;

    private Integer parentId;

    private Integer seq;

    private String remark;
}
