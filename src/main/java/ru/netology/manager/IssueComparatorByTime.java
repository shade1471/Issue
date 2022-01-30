package ru.netology.manager;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorByTime implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDate() - o2.getDate();
    }

    @Override
    public Comparator<Issue> reversed() {
        return Comparator.super.reversed();
    }
}
