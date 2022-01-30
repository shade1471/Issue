package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.manager.IssueManager;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RepositoryTest {

    private Repository repo = new Repository();
    private IssueManager manager = new IssueManager(repo);

    Issue one = new Issue(1,
            "shade1471",
            20220101,
            "Новая фича",
            "Давайте добавим поиск",
            true,
            Set.of("TMS", "management"),
            Set.of(),
            Set.of("enhancement", "help"),
            Set.of("admin"),
            List.of(new Comment(1, 20220102, "alex", "а почему бы и нет!"), new Comment(2, 20220109, "max", "Завтра и начнем!"))
    );

    Issue two = new Issue(2,
            "Alex",
            20220129,
            "Кривой GUI?",
            "Перенести кнопку авторизации",
            false,
            Set.of("TMS"),
            Set.of(),
            Set.of("bug"),
            Set.of("admin"),
            List.of());

    Issue three = new Issue(3,
            "Andrey",
            20220203,
            "Реализации для коллекций",
            "Поменять реализацию коллекции",
            true,
            Set.of("TMS"),
            Set.of(),
            Set.of("enhancement"),
            Set.of("shade1471"),
            List.of(new Comment(1, 20220206, "max", "Какую предлагаешь?")));

    Issue four = new Issue(4,
            "Andrey",
            20220405,
            "Новая АPI",
            "Кейсы для тестирования API",
            false,
            Set.of("API"),
            Set.of(),
            Set.of("help"),
            Set.of("shade1471"),
            List.of(new Comment(1, 20220410, "max", "Кто будет писать?"), new Comment(2, 20220411, "shade1471", "Ok, напишу")));

    @Test
    void shouldSaveIssue() {
        repo.save(one);

        List expected = List.of(one);
        List actual = manager.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByIdIfExist() {
        repo.save(one);
        repo.save(two);
        repo.save(three);
        repo.save(four);

        Issue expected = three;
        Issue actual = repo.getById(3);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByIdIfNotExist() {
        repo.save(one);
        repo.save(two);
        repo.save(three);
        repo.save(four);

        Issue actual = repo.getById(5);

        assertEquals(null, actual);
    }

    @Test
    void shouldRemoveIssue() {
        repo.save(one);
        repo.save(two);
        repo.save(three);
        repo.save(four);

        repo.remove(three);

        List expected = List.of(one, two, four);
        List actual = repo.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldClearCollection() {
        repo.save(one);
        repo.save(two);
        repo.save(three);
        repo.save(four);

        repo.clear();

        List expected = List.of();
        List actual = repo.getAll();

        assertEquals(expected, actual);
    }

}
