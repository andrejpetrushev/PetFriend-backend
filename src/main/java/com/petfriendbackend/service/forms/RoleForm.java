package com.petfriendbackend.service.forms;

import com.petfriendbackend.model.User;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RoleForm {

    @NotNull
    private User user;

    @Valid
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

}
