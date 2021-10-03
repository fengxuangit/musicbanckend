package com.evalshell.bean.third;

import lombok.Data;

@Data
public class WechatUserInfo {
    private String code;

    private String rawData;

    private String signature;

    private String encrypteData;

    private String iv;
}
