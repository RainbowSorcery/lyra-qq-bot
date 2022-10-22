package com.lyra.qqbot.service;

import com.lyra.qqbot.Strategy.qbot.send.IQBotSendMessageServiceStrategy;

public interface IRandomSexImgService extends IQBotSendMessageServiceStrategy {
    void getRandomSexImg(String messageType, Long groupId, Long userId);
}
