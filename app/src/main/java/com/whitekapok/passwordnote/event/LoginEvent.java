package com.whitekapok.passwordnote.event;

import com.whitekapok.passwordnote.entity.UserEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * Created by Lance on 2017/6/9.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginEvent extends BaseEvent{
    private UserEntity userEntity;

    public LoginEvent(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
