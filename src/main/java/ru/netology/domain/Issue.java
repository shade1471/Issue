package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private List<Comment> comment;                        // комменты

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && date == issue.date && isOpened == issue.isOpened && Objects.equals(author, issue.author) && Objects.equals(title, issue.title) && Objects.equals(text, issue.text) && Objects.equals(project, issue.project) && Objects.equals(milesStone, issue.milesStone) && Objects.equals(label, issue.label) && Objects.equals(assignee, issue.assignee) && Objects.equals(comment, issue.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, date, title, text, isOpened, project, milesStone, label, assignee, comment);
    }
}
