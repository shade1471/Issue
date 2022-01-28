package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;



class RepositoryTest {
    Issue issue = new Issue();

    private Repository repo = new Repository();
    private IssueManager manager = new IssueManager(repo);
    Issue one = new Issue(1, "shade1471", "очень плохо сделано, надо поправить", true);
    Issue two = new Issue(2, "Максим", "Добавить метод- удалить все", false);
    Issue three = new Issue(3, "Мария", "Хочу больше желтого цвета в интерфейсе", true);
    Issue four = new Issue(4, "shade1471", "Верстка сыпится в мобильной версии", false);
    Issue five = new Issue(5, "Максим", "Добавить функционал по аторизации через соц.сети", true);
    Issue six = new Issue(6, "Елена", "Поиск по id проблемы", false);


    @Test
    void shouldAddIssue() {
        repo.add(one);
        repo.add(two);
        repo.add(three);

        repo.getAll();

    }

    @Test
    void shouldAddMultipleProducts() {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue());
        issues.add(new Issue());

        repo.addAll(issues);

        repo.getAll();
        repo.getAll();
    }

    @Test
    void shouldGetOpenIssues() {
        repo.add(one);
        repo.add(two);
        repo.add(three);
        repo.add(four);
        repo.add(five);
        repo.add(six);

        List<Issue> actual = manager.findOpen();
    }

    @Test
    void shouldGetClosedIssues() {
        repo.add(one);
        repo.add(two);
        repo.add(three);
        repo.add(four);
        repo.add(five);
        repo.add(six);

      List tmp = manager.findClosed();

      List<Issue> expected = asList(two, four, six);

    }

    @Test
    void shouldClosedIssue() {
        repo.add(one);
        repo.add(two);
        repo.add(three);
        repo.add(four);
        repo.add(five);
        repo.add(six);

        Issue issue1 = repo.getById(2);

        manager.update(2);

        List<Issue> after = repo.getAll();

        Issue issue2 = repo.getById(2);


    }


}
