package com.miaoshao.demo.two.exception;

import com.miaoshao.demo.two.result.CodeMsg;

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
