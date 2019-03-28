package ru.job4j.hotel;

import java.util.Objects;

public class Rating implements Comparable<Rating> {
    private Integer evaluation;

    public Rating(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(evaluation, rating.evaluation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluation);
    }

    @Override
    public int compareTo(Rating input) {
        return this.evaluation.compareTo(input.evaluation);
    }
}
