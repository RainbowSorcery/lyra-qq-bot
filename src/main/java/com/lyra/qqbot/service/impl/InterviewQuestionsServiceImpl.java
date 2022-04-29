package com.lyra.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.qqbot.common.enums.MessageType;
import com.lyra.qqbot.entity.InterviewQuestionsEntity;
import com.lyra.qqbot.entity.SendMessageResultEntity;
import com.lyra.qqbot.exception.MyGraceException;
import com.lyra.qqbot.mapper.InterviewQuestionsMapper;
import com.lyra.qqbot.service.IInterviewQuestionsService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewQuestionsServiceImpl extends ServiceImpl<InterviewQuestionsMapper, InterviewQuestionsEntity> implements IInterviewQuestionsService {

    @Autowired
    private InterviewQuestionsMapper interviewQuestionsMapper;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Override
    public void sendRandomInterviewQuestion(String messageType, Long groupId, Long userId) {
        Long count = interviewQuestionsMapper.getInterviewQuestionCount();
        Long random = Double.valueOf(Math.random() * count).longValue();

        InterviewQuestionsEntity interviewQuestionsEntity  = interviewQuestionsMapper.selectOneInterviewQuestion(random);

        String sendMessage = "问题：" + interviewQuestionsEntity.getQuestion() + "\n" +
                "答案：" + interviewQuestionsEntity.getAnswer() + "\n" +
                "该题被输出：" + interviewQuestionsEntity.getPrintCount() + "次。";



        SendMessageResultEntity sendMessageResultEntity = qqBotUtils.sendMessage(messageType, userId, groupId, sendMessage);

        if (sendMessageResultEntity.getStatus().equals("failed")) {
            throw new MyGraceException("错误信息：" + sendMessageResultEntity.getWording(), messageType, userId, groupId);
        }

        interviewQuestionsEntity.setPrintCount(interviewQuestionsEntity.getPrintCount() + 1);

        interviewQuestionsMapper.updateById(interviewQuestionsEntity);

    }
}
