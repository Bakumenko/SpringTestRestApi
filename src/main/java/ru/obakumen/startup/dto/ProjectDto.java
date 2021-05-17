package ru.obakumen.startup.dto;

public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private float income;
    private String username;

    public ProjectDto(Long id, String title, String description, float income, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.income = income;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getIncome() {
        return income;
    }

    public String getUsername() {
        return username;
    }
}
