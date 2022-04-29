package com.lyra.qqbot;

import com.lyra.qqbot.service.IInterviewQuestionsService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QqBotApplicationTests {
    @Autowired
    private QQBotUtils qqBotUtils;

    @Autowired
    private IInterviewQuestionsService interviewQuestionsService;

    @Test
    void contextLoads() {

    }

}
