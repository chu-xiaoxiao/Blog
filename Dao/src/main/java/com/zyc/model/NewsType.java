package com.zyc.model;

/**
 * Created by YuChen Zhang on 17/09/25.
 */
public enum NewsType {
    ALLNEWS ("all"),
    VIDEO("video"),
    IMG("img"),
    GUONEI("guonei"),
    GUOWAI("guowai"),
    SHEHUI("shehui"),
    TIYU("tiyu"),
    CAIJING("caijing"),
    YULE("yule"),
    KEJI("keji"),
    JUNSHI("junshi");
    private String type;
    NewsType(String input) {
        this.type=input;
    }

    public String getType() {
        return type;
    }
}
