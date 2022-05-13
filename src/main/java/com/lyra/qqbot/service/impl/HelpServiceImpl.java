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

        String stringBuilder = "1.随机面试题。\n" +
                "2.随机色图。\n" +
                "3.随机r18色图。\n" +
                "4.获取七天内vpn签到信息。";

        qqBotUtils.sendMessage(messageType, userId, groupId, stringBuilder, true);
    }
}
