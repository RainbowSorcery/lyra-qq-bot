package com.lyra.qqbot.Strategy;

public interface IQBotSendMessageServiceStrategy {
    void sendMessage(String messageType, Long groupId, Long userId);
}
