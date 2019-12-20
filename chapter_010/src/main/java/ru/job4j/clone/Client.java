package ru.job4j.clone;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class Client implements Cloneable {
    private String name;
    private int age;
    private char sex;

    public Client(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Client{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex + '}';
    }

    @Override
    public Client clone() {
        Client result = null;
        try {
            result = (Client) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
