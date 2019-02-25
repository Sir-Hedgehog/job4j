package ru.job4j.generic;

import java.util.Iterator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoresTest {

    @Test
    public void whenUseUserStoreThenShowsItsFunctional() {
        UserStore users = new UserStore();
        User user = new User("123");
        User user1 = new User("456");
        User user2 = new User("789");
        users.add(user);
        users.add(user1);
        users.add(user2);
        assertThat(users.delete(user.getId()), is(true));
        assertThat(users.findById("456"), is(user1));
        assertThat(users.replace(user2.getId(), new User("834")), is(true));
    }

    @Test
    public void whenUseRoleStoreThenShowsItsFunctional() {
        RoleStore roles = new RoleStore();
        Role role = new Role("123");
        Role role1 = new Role("456");
        Role role2 = new Role("789");
        roles.add(role);
        roles.add(role1);
        roles.add(role2);
        assertThat(roles.delete(role.getId()), is(true));
        assertThat(roles.findById("456"), is(role1));
        assertThat(roles.replace(role2.getId(), new User("834")), is(true));
    }
}
