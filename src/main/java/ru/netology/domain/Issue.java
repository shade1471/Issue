package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
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
    private List<Comment> comment;                        // комменты

}
