package ru.job4j.stream;

public class Student {
    private int score;

    Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                '}';
    }
}

