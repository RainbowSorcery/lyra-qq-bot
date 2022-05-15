package com.lyra.qqbot.Strategy;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lyra.qqbot.cnstant.QQBotServiceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private RandomPonyFeaturedImgStrategy randomPonyFeaturedImgStrategy;


    @PostConstruct
    public void initStrategyMap() {
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_HELP_MESSAGE.getMessage(), sendHelpStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_7_DAY_SIGN_MESSAGE.getMessage(), send7DayVpnSignMessage);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RANDOM_PREVIEW_QUESTION.getMessage(), sendRandomInterviewQuestionStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_PONY_FEATURED_IMG.getMessage(), randomPonyFeaturedImgStrategy);
    }


    public IQBotSendMessageServiceStrategy getService(String type) {
        if (StringUtils.isNotEmpty(type)) {
            IQBotSendMessageServiceStrategy iqBotSendMessageServiceStrategy = sendMessageServiceStrategyMap.get(type);
            return iqBotSendMessageServiceStrategy;
        }

        return null;
    }
}
