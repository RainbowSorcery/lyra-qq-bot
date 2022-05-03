package com.lyra.qqbot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

@TableName(value = "sign_vpn_log")
public class Log {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String log;
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
