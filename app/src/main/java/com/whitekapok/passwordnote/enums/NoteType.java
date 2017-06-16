package com.whitekapok.passwordnote.enums;

import lombok.Getter;

/**
 *
 * Created by Lance on 2017/6/16.
 */

public enum NoteType {
    TYPE_TOP(1),        //置顶
    TYPE_NORMAL(0);     //正常
    @Getter private int type;

    NoteType(int type) {
        this.type = type;
    }
}
