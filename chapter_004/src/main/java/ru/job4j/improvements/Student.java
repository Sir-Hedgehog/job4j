package ru.job4j.improvements;


import java.util.Objects;

class Student {
    private String name;
    private int scope;

    Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    int getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", scope=" + scope
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scope == student.scope && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }
}
