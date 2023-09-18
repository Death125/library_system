package com.library.dto;

import jakarta.validation.constraints.NotEmpty;

public class MemberData {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String memberName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
