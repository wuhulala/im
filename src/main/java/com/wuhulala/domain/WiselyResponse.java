package com.wuhulala.domain;

/**
 * @author xueaohui
 *
 * 服务器响应信息
 */
public class WiselyResponse {
    private String responseMessage;

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
