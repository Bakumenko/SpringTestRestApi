package ru.obakumen.startup.dto;

import javax.persistence.Column;

public class ProjectDto {
    private String title;

    private String description;

    private float income;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getIncome() {
        return income;
    }
}
