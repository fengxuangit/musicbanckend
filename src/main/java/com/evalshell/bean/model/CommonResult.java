package com.evalshell.bean.model;

import lombok.Data;

@Data
public final class CommonResult<T> {
    private int status = 1;
    private String errorCode = "";
    private String errorMsg = "";
    private T resultBody;

    public CommonResult() {
    }

    public CommonResult(T resultBody) {
        this.resultBody = resultBody;
    }
}
