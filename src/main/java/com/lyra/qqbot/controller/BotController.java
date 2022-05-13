package com.lyra.qqbot.controller;

import com.lyra.qqbot.entity.ListenerEntity;
import com.lyra.qqbot.service.IHelpService;
import com.lyra.qqbot.service.IInterviewQuestionsService;
import com.lyra.qqbot.service.IRandomSexImgService;
import com.lyra.qqbot.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BotController {
    @Autowired
    private IInterviewQuestionsService interviewQuestionsService;

    @Autowired
    private IHelpService helpService;

    @Autowired
    private ISignService signService;

    @Autowired
    private IRandomSexImgService randomSexImgService;



    @PostMapping("/")
    public void listener(@RequestBody ListenerEntity listenerBody) {
        if ("随机面试题".equals(listenerBody.getMessage())) {
            interviewQuestionsService.sendRandomInterviewQuestion(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id());
        } else if ("/help".equals(listenerBody.getMessage())) {
            helpService.sendHelpList(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id());
        } else if ("获取七天内vpn签到信息".equals(listenerBody.getMessage())) {
            signService.get7TotalSignLog(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id());
        } else if ("随机色图".equals(listenerBody.getMessage())) {
            randomSexImgService.getRandomSexImg(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id(), false);
        }  else if ("随机r18色图".equals(listenerBody.getMessage())) {
            randomSexImgService.getRandomSexImg(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id(), true);
        }

    }

}
