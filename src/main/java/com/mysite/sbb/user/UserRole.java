package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
   ADMIN("ADMIN_ROLE"),
   USER("USER_ROLE");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
