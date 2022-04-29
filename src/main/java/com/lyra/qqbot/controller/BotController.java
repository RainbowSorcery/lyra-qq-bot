package com.lyra.qqbot.controller;

import com.lyra.qqbot.entity.ListenerEntity;
import com.lyra.qqbot.service.IInterviewQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BotController {
    @Autowired
    private IInterviewQuestionsService interviewQuestionsService;

    @PostMapping("/")
    public void listener(@RequestBody ListenerEntity listenerBody) {
        if ("随机面试题".equals(listenerBody.getMessage())) {
            interviewQuestionsService.sendRandomInterviewQuestion(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id());
        }

    }

}
