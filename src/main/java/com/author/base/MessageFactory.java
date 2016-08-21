package com.author.base;

/**
 * Created by Administrator on 2016/8/8.
 */


import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;

public class MessageFactory {
    private MessageSource messageSource;
    private final String SAVE_SUCCESS_PROPERTY = "ddl.save.success";
    private final String UPDATE_SUCCESS_PROPERTY = "ddl.update.success";
    private final String DELETE_SUCCESS_PROPERTY = "ddl.delete.success";
    private static String saveSuccessMsg;
    private static String updateSuccessMsg;
    private static String deleteSuccessMsg;

    public MessageFactory() {
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Message save() {
        Message msg = new Message(true, this.getSaveSuccessMsg());
        return msg;
    }

    public Message save(Object obj) {
        Message msg = this.save();
        msg.setData(obj);
        return msg;
    }

    public Message update() {
        Message msg = new Message(true, this.getUpdateSuccessMsg());
        return msg;
    }

    public Message update(Object obj) {
        Message msg = new Message(true, this.getUpdateSuccessMsg());
        msg.setData(obj);
        return msg;
    }

    public Message delete() {
        Message msg = new Message(true, this.getDeleteSuccessMsg());
        return msg;
    }

    public Message getObject(Object obj) {
        Message msg = new Message(obj);
        return msg;
    }

    public Message query(List list) {
        Message msg = new Message(list);
        return msg;
    }

    public Message query(Page page) {
        Long totalCount = Long.valueOf(page.getTotalElements());
        Message msg = new Message(Integer.valueOf(totalCount.intValue()), page.getContent());
        return msg;
    }

    public Message successTest() {
        Message msg = new Message();
        msg.setSuccess(Boolean.valueOf(true));
        msg.setMessage("测试成功");
        return msg;
    }

    public Message exceptionTest() {
        Message msg = new Message();
        msg.setIsException(Boolean.valueOf(true));
        msg.setExName("NullPointerException");
        msg.setExDetails("异常测试");
        return msg;
    }

    public Message sessionOutTest() {
        Message msg = new Message();
        msg.setIsSessionOut(Boolean.valueOf(true));
        return msg;
    }

    public String getSaveSuccessMsg() {
        if(saveSuccessMsg == null) {
            saveSuccessMsg = this.getMessage("ddl.save.success");
        }

        return saveSuccessMsg;
    }

    public String getUpdateSuccessMsg() {
        if(updateSuccessMsg == null) {
            updateSuccessMsg = this.getMessage("ddl.update.success");
        }

        return updateSuccessMsg;
    }

    public String getDeleteSuccessMsg() {
        if(deleteSuccessMsg == null) {
            deleteSuccessMsg = this.getMessage("ddl.delete.success");
        }

        return deleteSuccessMsg;
    }

    private String getMessage(String propertyName) {
        return this.messageSource.getMessage(propertyName, (Object[])null, (Locale)null);
    }

    public Message getMessageObject(String propertyName) {
        String msg = this.getMessage(propertyName);
        Message message = new Message(true, msg);
        return message;
    }
}
