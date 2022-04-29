package com.lyra.qqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyra.qqbot.entity.InterviewQuestionsEntity;

public interface InterviewQuestionsMapper extends BaseMapper<InterviewQuestionsEntity> {
    Long getInterviewQuestionCount();

    InterviewQuestionsEntity selectOneInterviewQuestion(Long randomCount);
}
