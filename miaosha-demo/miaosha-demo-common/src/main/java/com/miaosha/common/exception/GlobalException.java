package com.miaosha.common.exception;

import com.miaosha.common.result.CodeMsg;

public class GlobalException extends RuntimeException {

    private CodeMsg cm;

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.cm=codeMsg;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
