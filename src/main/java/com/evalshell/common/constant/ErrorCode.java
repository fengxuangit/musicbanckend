package com.evalshell.common.constant;

public enum ErrorCode {
    SUCCESS(200, "成功"),
    ERROR(101, "错误"),
    ERROR_INTERNAL(102, "内部错误");

    private int code;
    private String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
