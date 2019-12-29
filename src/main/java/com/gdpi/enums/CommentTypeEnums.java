package com.gdpi.enums;

public enum CommentTypeEnums {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnums(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {

        for (CommentTypeEnums commentTypeEnums : CommentTypeEnums.values()) {
            if (commentTypeEnums.getType() == type) {
                return true;
            }
        }

        return false;
    }
}
