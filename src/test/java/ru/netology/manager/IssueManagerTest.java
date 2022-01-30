package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Comment;
import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
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

    @BeforeEach
    void initEach() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
    }

    @Test
    void shouldFindAll() {

        List expected = List.of(one, two, three, four);
        List actual = manager.findAll();

        assertEquals(expected, actual);

    }

    @Test
    void sortedByCreationTimeOldest() {

        List expected = List.of(one, two, three, four);
        List actual = manager.sortByTimeOldest(new IssueComparatorByTime());

        assertEquals(expected, actual);
    }

    @Test
    void sortedByCreationTimeNewest() {

        List expected = List.of(four, three, two, one);
        List actual = manager.sortByTimeNewest(new IssueComparatorByTime());

        assertEquals(expected, actual);

    }

    @Test
    void sortByFewerComments() {

        List expected = List.of(two, three, one, four);
        List actual = manager.sortByLeastCommented(new IssueComparatorByComment());

        assertEquals(expected, actual);
    }

    @Test
    void sortByMostComments() {

        List expected = List.of(one, four, three, two);
        List actual = manager.sortByMostCommented(new IssueComparatorByComment());

        assertEquals(expected, actual);
    }

    @Test
    void filterByAuthor() {

        List expected = List.of(three, four);
        List actual = manager.filterByAuthor("Andrey");

        assertEquals(expected, actual);

    }

    @Test
    void filterByLabel() {

        List expected = List.of(one, four);
        List actual = manager.filterByLabel("help");

        assertEquals(expected, actual);

    }

    @Test
    void filterByAssignee() {

        List expected = List.of(one, two);
        List actual = manager.filterByAssignee("admin");

        assertEquals(expected, actual);

    }

    @Test
    void shouldUpdateIssueIfOpen() {

        manager.update(1);

        assertFalse(one.isOpened());

    }

    @Test
    void shouldUpdateIssueIfClosed() {

        manager.update(2);

        assertTrue(two.isOpened());

    }

    @Test
    void shouldFindAllOpenIssues() {

        List expected = List.of(one, three);
        List actual = manager.findOpen();

        assertEquals(expected, actual);

    }

    @Test
    void shouldFindAllClosedIssues() {

        List expected = List.of(two, four);
        List actual = manager.findClosed();

        assertEquals(expected, actual);

    }


}