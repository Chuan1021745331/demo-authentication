package com.chuan.authority.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuan.authority.sys.constants.SysDeptConstants;
import com.chuan.authority.sys.domain.SysDept;
import com.chuan.authority.sys.dto.SysDeptDto;
import com.chuan.authority.common.exception.DBException;
import com.chuan.authority.common.exception.ParamException;
import com.chuan.authority.sys.mapper.SysDeptMapper;
import com.chuan.authority.sys.param.SysDeptParam;
import com.chuan.authority.sys.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuan.authority.common.utile.HttpContextUtils;
import com.chuan.authority.common.utile.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JingChuan
 * @since 2018-08-29
 */
@Service
@Slf4j
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    public boolean save(SysDeptParam deptParam) {
        //判断是否能插入,同级别不能有相同的命名
        if (checkEquativeExsits(deptParam.getParentId(), deptParam.getName(), null)) {
            throw new ParamException("同一级别下不能存在相同的命名");
        }
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(deptParam,dept);
        //level计算
        dept.setLevel(caculateLevel(getLevel(dept.getParentId()),dept.getParentId()));
        //其他值
        dept.setOperator("sys_save");
        dept.setOperatorTime(LocalDateTime.now());
        String ipAddress = HttpServletUtils.getIPAddress(HttpContextUtils.getHttpServletRequest());
        dept.setOperatorIp(ipAddress);
        //入库
        try{
            return save(dept);
        }catch (Exception e){
            log.error("【新增部门失败】",e);
            throw new DBException("【数据库异常】:新增部门失败",e);
        }
    }

    @Override
    @Transactional
    public boolean update(SysDeptParam deptParam) {
        //判断是否能修改,同级别不能有相同的命名
        if (checkEquativeExsits(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一级别下不能存在相同的命名");
        }
        SysDept beforeDept = baseMapper.selectById(deptParam.getId());
        SysDept afterDept = new SysDept();
        BeanUtils.copyProperties(deptParam, afterDept);
        afterDept.setLevel(caculateLevel(getLevel(afterDept.getParentId()), afterDept.getParentId()));
        afterDept.setOperator("sys_update");
        afterDept.setOperatorTime(LocalDateTime.now());
        String ipAddress = HttpServletUtils.getIPAddress(HttpContextUtils.getHttpServletRequest());
        afterDept.setOperatorIp(ipAddress);
        try{
            boolean isUpdateSuccess = updateById(afterDept);
            if(isUpdateSuccess){
                //修改子部门
                updateChildLevel(beforeDept,afterDept);
            }
        }catch (Exception e){
            log.error("【修改部门失败】",e);
            throw new DBException("【数据库异常】:修改部门失败",e);
        }
        return true;
    }

    boolean updateChildLevel(SysDept beforeDept,SysDept afterDept){
        String beforPrefixLevel = beforeDept.getLevel();
        String afterPrefixLevel = afterDept.getLevel();
        //相同不用改
        if(beforPrefixLevel.equals(afterPrefixLevel)){
            return true;
        }
        List<SysDept> childDeptList = baseMapper.selectByLevel(beforPrefixLevel);
        childDeptList.forEach(childDept->{
            String beforeChildLevel = childDept.getLevel();
            int indexOfBefor = beforeChildLevel.indexOf(beforPrefixLevel);
            String afterChildLevel = StringUtils.join(afterPrefixLevel, beforeChildLevel.substring(indexOfBefor, beforeChildLevel.length()));
            childDept.setLevel(afterChildLevel);
        });
        //批量更新
        super.updateBatchById(childDeptList);
        return true;
    }

    /**
     * 获取部门树
     * @return
     */
    @Override
    public List<SysDeptDto> getDeptTree() {
        //获取所有部门
        QueryWrapper<SysDept> query = new QueryWrapper<>();
        List<SysDept> allDepts = baseMapper.selectList(query);
        //根据level分组,
        Map<String, List<SysDept>> deptMapList = allDepts.stream().collect(Collectors.groupingBy(dept ->dept.getLevel()));
        //获取第一个节点
        List<SysDeptDto> sysDeptDtoList = allDepts.stream()
                .filter(dept -> dept.getParentId() == SysDeptConstants.ROOT)
                .map(dept -> {
                    SysDeptDto sysDeptDto = new SysDeptDto();
                    BeanUtils.copyProperties(dept, sysDeptDto);
                    sysDeptDto.setChildDeptList(getChildDept(dept, deptMapList));
                    return sysDeptDto;
                }).collect(Collectors.toList());
        return sysDeptDtoList;
    }

    public List<SysDeptDto> getChildDept(SysDept dept,Map<String, List<SysDept>> deptMapList){
        String level=caculateLevel(dept.getLevel(),dept.getId());
        List<SysDept> sysDepts = deptMapList.get(level);
        if(sysDepts==null){
            return null;
        }
        List<SysDeptDto> sysDeptDtos = sysDepts.stream().sorted(Comparator.comparing(SysDept::getSeq)).map(childDept -> {
            SysDeptDto sysDeptDto = new SysDeptDto();
            BeanUtils.copyProperties(childDept, sysDeptDto);
            sysDeptDto.setChildDeptList(getChildDept(childDept, deptMapList));
            return sysDeptDto;
        }).collect(Collectors.toList());
        return sysDeptDtos;
    }

    /**
     * 判断统一级别是否存在当前命名
     *
     * @param parentId 父级id
     * @param name     被判断的命名
     * @param deptId   需要排除的id
     * @return
     */
    public boolean checkEquativeExsits(Integer parentId, String name, Integer deptId) {
        Integer count = baseMapper.selectEquativeCount(parentId, name, deptId);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     * 根据父节点
     * @param parentId
     * @return
     */
    public String caculateLevel(String parentLevel,Integer parentId){
        if(StringUtils.isBlank(parentLevel)){
            return SysDeptConstants.ROOT+"";
        }
        //在衔接自己的id
        return parentLevel + SysDeptConstants.LEVEL_SEPARATOR + parentId;
    }

    public String getLevel(Integer deptId){
        SysDept sysDept = baseMapper.selectById(deptId);
        if(sysDept==null){
            return null;
        }
        return sysDept.getLevel();
    }
}
