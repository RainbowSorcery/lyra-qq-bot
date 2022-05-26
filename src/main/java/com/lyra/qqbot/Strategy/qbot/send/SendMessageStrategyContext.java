package com.lyra.qqbot.Strategy.qbot.send;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lyra.qqbot.cnstant.PonyImageConstant;
import com.lyra.qqbot.cnstant.QQBotServiceEnum;
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
    private SendRandomPonyImageMessage sendRandomBackgroundPonyImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomLyraHeartstringsImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomTSImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomRDImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomRRImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomSnowdropImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomNyxImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomPinkiePieImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomFluttershyImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomApplejackImageMessage;

    @Autowired
    private SendRandomPonyImageMessage sendRandomLunaImageMessage;

    @Autowired
    private SendRandomMusicStrategy sendRandomMusicStrategy;



    @PostConstruct
    public void initStrategyMap() {
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_HELP_MESSAGE.getMessage(), sendHelpStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_7_DAY_SIGN_MESSAGE.getMessage(), send7DayVpnSignMessage);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RANDOM_PREVIEW_QUESTION.getMessage(), sendRandomInterviewQuestionStrategy);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_PONY_FEATURED_IMG.getMessage(), ponyFeaturedImgStrategy);
        sendRandomBackgroundPonyImageMessage.setImageType(PonyImageConstant.BACKGROUND_PONY);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_BACKGROUND_PONY_IMAGE.getMessage(), sendRandomBackgroundPonyImageMessage);
        sendRandomLyraHeartstringsImageMessage.setImageType(PonyImageConstant.LYRA_PONY);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_LYRA_HEARTSTRINGS_IMAGE.getMessage(), sendRandomLyraHeartstringsImageMessage);
        sendRandomTSImageMessage.setImageType(PonyImageConstant.TWILIGHT_SPARKLE);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_TS_IMAGE.getMessage(), sendRandomTSImageMessage);
        sendRandomRDImageMessage.setImageType(PonyImageConstant.RAINBOW_DASH);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RD_IMAGE.getMessage(), sendRandomRDImageMessage);
        sendRandomRRImageMessage.setImageType(PonyImageConstant.RR);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RR_IMAGE.getMessage(), sendRandomRRImageMessage);
        sendRandomSnowdropImageMessage.setImageType(PonyImageConstant.SNOWDROP);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_SNOWDROP_IMAGE.getMessage(), sendRandomSnowdropImageMessage);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_RANDOM_PONY_MUSIC.getMessage(), sendRandomMusicStrategy);

        sendRandomNyxImageMessage.setImageType(PonyImageConstant.NYX);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_NYX_IMAGE.getMessage(), sendRandomNyxImageMessage);
        sendRandomPinkiePieImageMessage.setImageType(PonyImageConstant.PINKIE_PIE);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_PINKIE_PIE_IMAGE.getMessage(), sendRandomPinkiePieImageMessage);


        sendRandomFluttershyImageMessage.setImageType(PonyImageConstant.FLUTTERSHY);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_FLUTTERSHY_IMAGE.getMessage(), sendRandomFluttershyImageMessage);


        sendRandomApplejackImageMessage.setImageType(PonyImageConstant.APPLEJACK);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_APPLEJACK_IMAGE.getMessage(), sendRandomApplejackImageMessage);

        sendRandomLunaImageMessage.setImageType(PonyImageConstant.LUNA);
        sendMessageServiceStrategyMap.put(QQBotServiceEnum.GET_LUNA_IMAGE.getMessage(), sendRandomLunaImageMessage);
    }


    public IQBotSendMessageServiceStrategy getService(String type) {
        if (StringUtils.isNotEmpty(type)) {
            return sendMessageServiceStrategyMap.get(type);
        }

        return null;
    }
}
