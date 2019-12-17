package ru.job4j.gc;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.12.2019
 */

public class User {
    public String name;
    public int age;
    public char sex;

    public User(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(String.format("Destroy object Of User: %s", this));
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex + '}';
    }
}
