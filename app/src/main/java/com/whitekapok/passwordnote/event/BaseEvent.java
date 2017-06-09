package com.whitekapok.passwordnote.event;

import com.whitekapok.passwordnote.enums.Status;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * Created by Lance on 2017/6/9.
 */

@Data
public class BaseEvent implements Serializable {
    private String message;
    private Status status;
}
