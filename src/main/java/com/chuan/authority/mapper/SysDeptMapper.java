package com.chuan.authority.mapper;

import com.chuan.authority.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JingChuan
 * @since 2018-08-29
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    Integer selectEquativeCount(@Param("parentId") Integer parentId, @Param("name") String name, @Param("deptId") Integer deptId);

}
