package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.service.IInterviewQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendRandomInterviewQuestionStrategy implements IQBotSendMessageServiceStrategy {
    @Autowired
    private IInterviewQuestionsService interviewQuestionsService;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        interviewQuestionsService.sendRandomInterviewQuestion(messageType, groupId, userId);
    }
}
