package com.lyra.qqbot.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class AdminUser {
    private Long id;
    private Long qq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }
}
