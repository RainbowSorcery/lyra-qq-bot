package com.lyra.qqbot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.qqbot.entity.InterviewQuestionsEntity;

public interface IInterviewQuestionsService extends IService<InterviewQuestionsEntity> {
    void sendRandomInterviewQuestion(String messageType, Long groupId, Long userId);
}
