package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Comment {

    private int id;
    private int date;              //формат ГГГГММДД
    private String author;
    private String text;

}
