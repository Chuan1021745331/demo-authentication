package com.chuan.authority.service;

import com.chuan.authority.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuan.authority.dto.sysdept.SysDeptDto;
import com.chuan.authority.param.SysDeptParam;

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
