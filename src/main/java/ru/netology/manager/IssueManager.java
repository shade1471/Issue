package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private Repository repo;

    public IssueManager(Repository repo) {
        this.repo = repo;
    }

    public void add(Issue issue) {
        repo.save(issue);
    }

    public List<Issue> findAll() {
        return repo.getAll();
    }

    private List<Issue> filterBy(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repo.getAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findOpen() {
        return filterBy(i -> i.isOpened() == true);
    }

    public List<Issue> findClosed() {
        return filterBy(i -> i.isOpened() == false);
    }

    public void update(int id) {
        Issue issue = repo.getById(id);
        if (issue.isOpened()) {
            issue.setOpened(false);
        } else {
            issue.setOpened(true);
        }
    }

    public List<Issue> sortByTimeOldest(Comparator<Issue> comparator) {
        List<Issue> result = repo.getAll();
        result.sort(new IssueComparatorByTime());
        return result;
    }

    public List<Issue> sortByTimeNewest(Comparator<Issue> comparator) {
        List<Issue> result = repo.getAll();
        result.sort(new IssueComparatorByTime().reversed());
        return result;
    }

    public List<Issue> sortByLeastCommented(Comparator<Issue> comparator) {
        List<Issue> result = repo.getAll();
        result.sort(new IssueComparatorByComment());
        return result;
    }

    public List<Issue> sortByMostCommented(Comparator<Issue> comparator) {
        List<Issue> result = repo.getAll();
        result.sort(new IssueComparatorByComment().reversed());
        return result;
    }

    public List<Issue> filterByAuthor(String author) {
        return filterBy(i -> i.getAuthor().equalsIgnoreCase(author));
    }

    public List<Issue> filterByLabel(String label) {
        return filterBy(i -> i.getLabel().contains(label));
    }

    public List<Issue> filterByAssignee(String assignee) {
        return filterBy(i -> i.getAssignee().contains(assignee));
    }
}
