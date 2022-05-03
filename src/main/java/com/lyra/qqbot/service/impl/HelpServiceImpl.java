package com.lyra.qqbot.service.impl;

import com.lyra.qqbot.service.IHelpService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpServiceImpl implements IHelpService {
    @Autowired
    private QQBotUtils qqBotUtils;

    @Override
    public void sendHelpList(String messageType, Long groupId, Long userId) {

        String stringBuilder = "1.随机面试题\n" +
                "2.test\n" +
                "3.test\n";

        qqBotUtils.sendMessage(messageType, userId, groupId, stringBuilder);
    }
}
