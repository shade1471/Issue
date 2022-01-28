package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository {
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> getAll(){
        return issues;
    }

    public Issue getById(int id){
        for(Issue issue: issues){
            if(issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public boolean add(Issue issue){
        return issues.add(issue);
    }

    public boolean remove(Issue issue){
        return issues.remove(issue);
    }

    public boolean addAll(Collection<? extends Issue> issues){
        return this.issues.addAll(issues);
    }

    public boolean removeAll(Collection<? extends Issue> issues){
        return this.issues.removeAll(issues);
    }

    public Issue[] toArray(){
        return this.toArray();
    }

}
