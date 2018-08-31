package com.chuan.authority.controller;


import com.chuan.authority.dto.sysdept.SysDeptDto;
import com.chuan.authority.param.SysDeptParam;
import com.chuan.authority.service.SysDeptService;
import com.chuan.authority.utile.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JingChuan
 * @since 2018-08-29
 */
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService deptService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public R saveDept(SysDeptParam deptParam){
        boolean save = deptService.save(deptParam);
        return R.ok(save);
    }

    @RequestMapping(value = "/tree",method = RequestMethod.POST)
    @ResponseBody
    public R getTree(){
        List<SysDeptDto> deptTree = deptService.getDeptTree();
        return R.ok(deptTree);
    }
}

