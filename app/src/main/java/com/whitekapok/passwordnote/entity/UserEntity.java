package com.whitekapok.passwordnote.entity;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * Created by Lance on 2017/5/31.
 */

@Data
public class UserEntity implements Serializable {
    private String uid;
    private String username;
    private String password;
}
