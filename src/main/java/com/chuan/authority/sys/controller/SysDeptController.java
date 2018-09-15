package com.chuan.authority.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chuan.authority.common.cache.redis.RedisService;
import com.chuan.authority.common.exception.CacheException;
import com.chuan.authority.sys.dto.SysDeptDto;
import com.chuan.authority.common.exception.ParamException;
import com.chuan.authority.sys.param.SysDeptParam;
import com.chuan.authority.sys.service.SysDeptService;
import com.chuan.authority.common.utile.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService deptService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public R saveDept(@Validated SysDeptParam deptParam, BindingResult error, HttpServletRequest request){
        if(error.hasErrors()){
            List<ObjectError> allErrors = error.getAllErrors();
            StringBuilder message = new StringBuilder();
            List<String> allErrMsg = allErrors.stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            message.append(StringUtils.join(allErrMsg,","));
            throw new ParamException(message.toString());
        }
        boolean save = deptService.save(deptParam);
        return R.ok(save);
    }

    @RequestMapping(value = "/tree",method = RequestMethod.POST)
    @ResponseBody
    public R getTree(){
        //先从缓存中获取
        List<SysDeptDto> deptTreeCache = redisService.callRedis(jedis -> {
            try {
                String sys_dept_treeStr = jedis.get("sys_dept_tree");
                return JSONArray.parseArray(sys_dept_treeStr, SysDeptDto.class);
            } catch (Exception e) {
                log.error("从缓存中获取失败", e);
                return null;
            }
        });
        if(deptTreeCache!=null){
            return R.ok(deptTreeCache);
        }
        //缓存中无值，则从数据库中获取，并将结果写入缓存
        List<SysDeptDto> deptTree = deptService.getDeptTree();
        redisService.excute(jedis -> {
            jedis.setex("sys_dept_tree",1200,JSON.toJSONString(deptTree));
        });
        return R.ok(deptTree);
    }
}

