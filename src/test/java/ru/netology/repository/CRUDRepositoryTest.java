package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Comment;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueComparatorByComment;
import ru.netology.manager.IssueComparatorByTime;
import ru.netology.manager.IssueManager;


import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

public class CRUDRepositoryTest {
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
            List.of(new Comment(1, 20220102, "alex", "а почему бы и нет!"), new Comment(2, 20220109, "max", "Завтра и начнем!")));

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

    @Nested
    public class Empty {

        @Test
        void shouldGetByIdIfNotExist() {
            Issue expected = null;
            Issue actual = repo.getById(3);

            assertEquals(expected, actual);
        }

        @Test
        void shouldSaveIssue() {
            repo.save(one);

            List expected = List.of(one);
            List actual = repo.getAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldGetAll() {
            List expected = List.of();
            List actual = repo.getAll();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleElement {

        @BeforeEach
        void initEach() {
            repo.save(one);
        }

        @Test
        void shouldGetByIdIfExist() {
            Issue expected = one;
            Issue actual = repo.getById(1);

            assertEquals(expected, actual);
        }

        @Test
        void shouldGetByIdIfNotExist() {
            Issue actual = repo.getById(2);
            assertEquals(null, actual);
        }

        @Test
        void shouldFilterByAuthorIfExist() {
            List expected = List.of(one);
            List actual = manager.filterByAuthor("shade1471");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthorIfNotExist() {
            List expected = List.of();
            List actual = manager.filterByAuthor("Andrey");

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveIssue() {
            repo.remove(one);

            List expected = List.of();
            List actual = repo.getAll();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class WithElements {
        @BeforeEach
        void initEach() {
            repo.save(one);
            repo.save(two);
            repo.save(three);
            repo.save(four);
        }

        @Test
        void shouldGetByIdIfExist() {
            Issue expected = three;
            Issue actual = repo.getById(3);

            assertEquals(expected, actual);
        }

        @Test
        void shouldGetByIdIfNotExist() {
            Issue actual = repo.getById(5);

            assertEquals(null, actual);
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
        void shouldSortByCreationTimeNewest() {
            List expected = List.of(four, three, two, one);
            List actual = manager.sortByTimeNewest(new IssueComparatorByTime());

            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByFewerComments() {
            List expected = List.of(two, three, one, four);
            List actual = manager.sortByLeastCommented(new IssueComparatorByComment());

            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByMostComments() {
            List expected = List.of(one, four, three, two);
            List actual = manager.sortByMostCommented(new IssueComparatorByComment());

            assertEquals(expected, actual);
        }

        @Test
        void ShouldFilterByAuthor() {
            List expected = List.of(three, four);
            List actual = manager.filterByAuthor("Andrey");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            List expected = List.of(one, four);
            List actual = manager.filterByLabel("help");

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
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

        @Test
        void shouldRemoveIssue() {
            repo.remove(three);

            List expected = List.of(one, two, four);
            List actual = repo.getAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldClearCollection() {
            repo.clear();

            List expected = List.of();
            List actual = repo.getAll();

            assertEquals(expected, actual);
        }
    }
}