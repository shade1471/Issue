package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Issue {
    private int id;
    private String author;                                // автор
    private int date;                                     // дата создания, формат ГГГГММДД
    private String title;                                 // заголовок
    private String text;                                  // текстовая часть issue
    private boolean isOpened;                             // статус issue

    private Set<String> project;                          // теги привязки к проектам
    private Set<String> milesStone;                       // теги по вехам
    private Set<String> label;                            // ярлыки
    private Set<String> assignee;                         // назначенные
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", isOpened=" + isOpened +
                ", project=" + project +
                ", milesStone=" + milesStone +
                ", label=" + label +
                ", assignee=" + assignee +
                '}';
    }
}
