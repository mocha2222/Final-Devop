package com.example.idcard_management.model;

public class ProfileBuilder {

    public static Profile buildStudent() {
        return Profile.builder()
                .profileType(ProfileType.STUDENT)
                .department("IT")
                .build();
    }

    public static Profile buildEmployee() {
        return Profile.builder()
                .profileType(ProfileType.EMPLOYEE)
                .department("ADMIN")
                .build();
    }

    public static Profile buildUser() {
        return Profile.builder()
                .profileType(ProfileType.USER)
                .build();
    }
}
