package com.chuan.authority.sys.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(message = "部门名称不能为空")
    private String name;

    private Integer parentId;

    @Min(value = 1,message = "排序字段不能小于1")
    private Integer seq;

    private String remark;
}
