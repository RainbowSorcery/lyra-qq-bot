package com.lyra.qqbot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.qqbot.entity.PonyMusic163;

public interface IPonyMusic163Service extends IService<PonyMusic163> {
    void sendRandomMusic(String messageType, Long groupId, Long userId);
}
