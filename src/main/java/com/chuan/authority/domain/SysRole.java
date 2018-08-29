package com.chuan.authority.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JingChuan
 * @since 2018-08-29
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 类型 1 管理员角色 2普通角色
     */
    private Integer type;

    /**
     * 状态 1正常 0冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后一次操作的人
     */
    private String operator;

    private LocalDateTime operatorTime;

    private String operatorIp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(LocalDateTime operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", status=" + status +
        ", remark=" + remark +
        ", operator=" + operator +
        ", operatorTime=" + operatorTime +
        ", operatorIp=" + operatorIp +
        "}";
    }
}
