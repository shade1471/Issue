package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Issue> issues = new ArrayList<>();

    public boolean save(Issue issue) {
        return issues.add(issue);
    }

    public List<Issue> getAll() {
        return issues;
    }

    public boolean remove(Issue issue) {
        return issues.remove(issue);
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void clear() {
        this.issues.clear();
    }

}
