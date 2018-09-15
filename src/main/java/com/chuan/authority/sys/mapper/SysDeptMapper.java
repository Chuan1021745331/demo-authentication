package com.chuan.authority.sys.mapper;

import com.chuan.authority.sys.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<SysDept> selectByLevel(String level);
}
