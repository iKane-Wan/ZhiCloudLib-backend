package com.kane.pattern.chain;

import com.kane.entity.bo.MessageBO;
import lombok.Setter;

@Setter
public abstract class AbstractRegisterHandler {

    protected AbstractRegisterHandler successor;

    public abstract void handle(MessageBO bo);
}
