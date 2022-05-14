package com.lyra.qqbot.cnstant;

public enum QQBotServiceEnum {
    GET_RANDOM_PREVIEW_QUESTION("随机面试题"),
    GET_HELP_MESSAGE("/help"),
    GET_7_DAY_SIGN_MESSAGE("获取七天内vpn签到信息")
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
