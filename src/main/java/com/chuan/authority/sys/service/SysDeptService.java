package com.chuan.authority.sys.service;

import com.chuan.authority.sys.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuan.authority.sys.dto.SysDeptDto;
import com.chuan.authority.sys.param.SysDeptParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JingChuan
 * @since 2018-08-29
 */
public interface SysDeptService extends IService<SysDept> {
    boolean save(SysDeptParam deptParam);

    List<SysDeptDto> getDeptTree();

    boolean update(SysDeptParam deptParam);
}
