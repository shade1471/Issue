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

    public List<Issue> findOpen() {
        List<Issue> result = new ArrayList<>();
        List<Issue> issues = repo.getAll();
        for (Issue issue : issues) {
            if (issue.isOpened()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findClosed() {
        List<Issue> result = new ArrayList<>();
        List<Issue> issues = repo.getAll();
        for (Issue issue : issues) {
            if (issue.isOpened() == false) {
                result.add(issue);
            }
        }
        return result;
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
        comparator = new IssueComparatorByTime();
        result.sort(comparator);
        return result;

    }

    public List<Issue> sortByTimeNewest(Comparator<Issue> comparator) {
        List<Issue> result = repo.getAll();
        comparator = new IssueComparatorByTime();
        result.sort(comparator.reversed());
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
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repo.getAll()) {
            if (issue.getAuthor().contains(author)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repo.getAll()) {
            if (issue.getLabel().contains(label)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repo.getAll()) {
            if (issue.getAssignee().contains(assignee)) {
                result.add(issue);
            }
        }
        return result;
    }

}
