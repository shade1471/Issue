package ru.netology.manager;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorByComment implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getComment().size() - o2.getComment().size();
    }

    @Override
    public Comparator<Issue> reversed() {
        return Comparator.super.reversed();
    }
}
