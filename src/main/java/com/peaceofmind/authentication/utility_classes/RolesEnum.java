package com.peaceofmind.authentication.utility_classes;

public enum RolesEnum {
    PATIENT,
    COUNSELOR,
    ADMIN;

    
    public static RolesEnum fromString(String value) {
        return RolesEnum.valueOf(value.toUpperCase());
    }
}
