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
public class SysAcim implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer parentId;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 状态 ;1正常 0冻结 
     */
    private Integer status;

    private String remark;

    private Integer seq;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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
        return "SysAcim{" +
        "id=" + id +
        ", name=" + name +
        ", parentId=" + parentId +
        ", level=" + level +
        ", status=" + status +
        ", remark=" + remark +
        ", seq=" + seq +
        ", operator=" + operator +
        ", operatorTime=" + operatorTime +
        ", operatorIp=" + operatorIp +
        "}";
    }
}
