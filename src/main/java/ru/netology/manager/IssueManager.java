package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private Repository repo;

    public IssueManager(Repository repo) {
        this.repo = repo;
    }

    public void addIssue(Issue issue) {
        repo.add(issue);
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

//    public List<Issue> filterBy(String author, Predicate<String> filter){
//        filter = new Filter();
//        List<Issue> result = new ArrayList<>();
//        for(Issue issue : repo.getAll()){
//            if (filter.test(author)
//        }
//
//        return result;
//
//
//    }
}
