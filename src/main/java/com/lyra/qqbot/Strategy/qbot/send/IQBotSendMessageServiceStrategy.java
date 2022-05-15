package com.lyra.qqbot.Strategy.qbot.send;

public interface IQBotSendMessageServiceStrategy {
    void sendMessage(String messageType, Long groupId, Long userId);
}
