package com.lyra.qqbot.cnstant;

public enum QQBotServiceEnum {
    GET_RANDOM_PREVIEW_QUESTION("随机面试题"),
    GET_HELP_MESSAGE("/help"),
    GET_7_DAY_SIGN_MESSAGE("获取七天内vpn签到信息"),
    GET_PONY_FEATURED_IMG("获取特殊小马图片"),
    GET_BACKGROUND_PONY_IMAGE("随机背景小马天琴图"),
    GET_LYRA_HEARTSTRINGS_IMAGE("随机天琴图"),
    GET_TS_IMAGE("随机ts图"),
    GET_RD_IMAGE("随机rd图"),
    GET_RR_IMAGE("随机rr图"),
    GET_SNOWDROP_IMAGE("随机落雪图")
    ;
    private String message;

    QQBotServiceEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
