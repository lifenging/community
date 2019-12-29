package com.gdpi.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "你在的问题不在了，要不然你换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作还未登录，请先登录后再试"),
    SYS_ERROR(2004, "服务器冒烟了，请稍后试试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你回复的评论不在了，要不然你换个试试？"),
    COMMENT_IS_EMPTY(2007, "输入的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读取别人的信息呢！！！"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非不翼而飞了？"),
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
