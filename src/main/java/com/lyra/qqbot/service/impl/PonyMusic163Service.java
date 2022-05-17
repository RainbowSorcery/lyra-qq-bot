package com.lyra.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.qqbot.entity.PonyMusic163;
import com.lyra.qqbot.mapper.PonyMusic163Mapper;
import com.lyra.qqbot.service.IPonyMusic163Service;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PonyMusic163Service extends ServiceImpl<PonyMusic163Mapper, PonyMusic163>  implements IPonyMusic163Service {
    @Autowired
    private PonyMusic163Mapper ponyMusic163Mapper;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Override
    public void sendRandomMusic(String messageType, Long groupId, Long userId) {
        List<PonyMusic163> ponyMusic163s = ponyMusic163Mapper.selectList(null);

        int i = (int) (Math.random() * ponyMusic163s.size());

        qqBotUtils.send163MusicMessage(messageType, userId, groupId, ponyMusic163s.get(i).getId());
    }
}
