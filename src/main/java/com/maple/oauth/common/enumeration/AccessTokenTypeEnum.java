package com.maple.oauth.common.enumeration;

public enum AccessTokenTypeEnum {

    CLIENT_MODE("client"),

    ;

    public String type;

    AccessTokenTypeEnum(String type) {
        this.type = type;
    }


}
