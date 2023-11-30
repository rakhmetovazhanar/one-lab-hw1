package com.example.onelab1.dto;

public class CourseSaveRequestDto {
    private String name;
    private String newName;

    public CourseSaveRequestDto() {}
    public CourseSaveRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNewName() {
        return newName;
    }
}
