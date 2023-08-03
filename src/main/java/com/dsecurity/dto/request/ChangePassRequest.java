package com.dsecurity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassRequest {
    private String currentPassword;
    private String newPassword;
}
