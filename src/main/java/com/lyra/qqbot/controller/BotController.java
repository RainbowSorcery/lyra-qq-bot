package com.lyra.qqbot.controller;

import com.lyra.qqbot.Strategy.IQBotSendMessageServiceStrategy;
import com.lyra.qqbot.Strategy.SendMessageStrategyContext;
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
    private SendMessageStrategyContext sendMessageStrategyContext;



    @PostMapping("/")
    public void listener(@RequestBody ListenerEntity listenerBody) {
        IQBotSendMessageServiceStrategy service = sendMessageStrategyContext.getService(listenerBody.getMessage());
        if (service != null) {
            service.sendMessage(listenerBody.getMessage_type(), listenerBody.getGroup_id(), listenerBody.getUser_id());
        }
    }

}
