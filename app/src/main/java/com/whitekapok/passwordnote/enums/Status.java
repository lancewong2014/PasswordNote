package com.whitekapok.passwordnote.enums;

import lombok.Getter;

/**
 * 请求状态码
 * Created by Lance on 2017/4/26.
 */

public enum Status {
    STATUS_SUCCESS(1),
    STATUS_UPDATE(2),
    STATUS_FAIL(-1);
    @Getter private int status;
    Status(int status){
        this.status=status;
    }
}
