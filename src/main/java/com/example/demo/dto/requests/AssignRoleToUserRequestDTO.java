package com.example.demo.dto.requests;

import com.example.demo.validations.role.ValidRoleId;
import com.example.demo.validations.user.ValidUserId;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssignRoleToUserRequestDTO {
    @NotNull
    @ValidUserId
    private Long userId;

    @NotNull
    @ValidRoleId
    private Long roleId;
}
