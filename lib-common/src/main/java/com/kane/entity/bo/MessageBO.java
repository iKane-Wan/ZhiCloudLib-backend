package com.kane.entity.bo;

import com.kane.enums.NotifyType;
import lombok.Data;

@Data
public class MessageBO {
    private NotifyType type;
    private Object obj;
}
