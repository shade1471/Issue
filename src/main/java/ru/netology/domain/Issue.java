package ru.netology.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Issue {
    private int id;
    private String author;
    private String description;
    private boolean isOpened;

    public Issue(int id, String author, String description, boolean isOpened) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.isOpened = isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", isOpened=" + isOpened +
                '}';
    }
}
