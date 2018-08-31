package com.chuan.authority.dto.sysdept;

import com.chuan.authority.domain.SysDept;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SysDeptDto extends SysDept {
    private List<SysDeptDto> childDeptList=new ArrayList<>();
}
