package ru.job4j.chat.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.09.2020
 */

@Entity(name = "Persons")
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "person_to_room",
            joinColumns = @JoinColumn(name = "person_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "room_id", nullable = false, updatable = false)
    )
    private Set<Room> rooms = new HashSet<>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id
                && age == person.age
                && Objects.equals(name, person.name)
                && sex == person.sex
                && Objects.equals(login, person.login)
                && Objects.equals(password, person.password)
                && Objects.equals(role, person.role)
                && Objects.equals(rooms, person.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, login, password, role);
    }

    @Override
    public String toString() {
        return "Person{"
                    + "id=" + id
                    + ", name='" + name + '\''
                    + ", age=" + age
                    + ", sex=" + sex
                    + ", login='" + login + '\''
                    + ", password='" + password + '\''
                    + ", role=" + role
                    + ", rooms=" + rooms
                + '}';
    }
}
