package com.wuhulala.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xueaohui
 */
public class Response {
    private String sender;
    private Date date;
    private String text;

    public String getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }


    public Response(String sender, Date date, String text) {
        this.sender = sender;
        this.date = date;
        this.text = text;
    }
}
