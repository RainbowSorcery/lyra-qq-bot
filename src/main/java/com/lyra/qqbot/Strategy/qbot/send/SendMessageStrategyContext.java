package com.lyra.qqbot.Strategy.qbot.send;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lyra.qqbot.cnstant.QQBotServiceEnum;
import com.lyra.qqbot.processor.PonyImagePageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMessageStrategyContext {
    private final Map<String, IQBotSendMessageServiceStrategy> sendMessageServiceStrategyMap =
            new HashMap<>();

    @Autowired
    private SendHelpStrategy sendHelpStrategy;

    @Autowired
    private Send7DayVpnSignMessage send7DayVpnSignMessage;

    @Autowired
    private SendRandomInterviewQuestionStrategy sendRandomInterviewQuestionStrategy;

    @Autowired
    private PonyFeaturedImgStrategy ponyFeaturedImgStrategy;

    @Autowired
    private SendRandomBackgroundPonyImageMessage sendRandomBackgroundPonyImageMessage;



    @PostConstruct
    public void initStrategyMap() {
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_HELP_MESSAGE.getMessage(), sendHelpStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_7_DAY_SIGN_MESSAGE.getMessage(), send7DayVpnSignMessage);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RANDOM_PREVIEW_QUESTION.getMessage(), sendRandomInterviewQuestionStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_PONY_FEATURED_IMG.getMessage(), ponyFeaturedImgStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_BACKGROUND_PONY_IMAGE.getMessage(), sendRandomBackgroundPonyImageMessage);
    }


    public IQBotSendMessageServiceStrategy getService(String type) {
        if (StringUtils.isNotEmpty(type)) {
            return sendMessageServiceStrategyMap.get(type);
        }

        return null;
    }
}
