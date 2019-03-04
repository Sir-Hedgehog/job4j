package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConnectedListTest {
    @Test
    public void whenFormElementsOfCircleThenShowsRhythm() {
        ConnectedList<Integer> list = new ConnectedList<>();
        ConnectedList.Node<Integer> first = new ConnectedList.Node<>(1);
        ConnectedList.Node<Integer> two = new ConnectedList.Node<>(2);
        ConnectedList.Node<Integer> third = new ConnectedList.Node<>(3);
        ConnectedList.Node<Integer> four = new ConnectedList.Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(list.hasCycle(first), is(true));
    }
}
