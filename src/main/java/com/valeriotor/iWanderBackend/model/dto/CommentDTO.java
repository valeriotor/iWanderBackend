package com.valeriotor.iWanderBackend.model.dto;

public class CommentDTO {
    private int dayIndex;
    private String text;

    public CommentDTO() {
    }

    public CommentDTO(int dayIndex, String text) {
        this.dayIndex = dayIndex;
        this.text = text;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
