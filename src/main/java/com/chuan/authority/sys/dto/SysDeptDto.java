package com.chuan.authority.sys.dto;

import com.chuan.authority.sys.domain.SysDept;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/30 21:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysDeptDto extends SysDept {
    private List<SysDeptDto> childDeptList=new ArrayList<>();
}
