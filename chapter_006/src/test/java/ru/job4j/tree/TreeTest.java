package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.03.2019
 */

public class TreeTest {
    @Test
    public void when6ElementsFindLastThen6() {
        Node<Integer> root = new Node(1);
        Tree<Integer> tree = new Tree<>(root);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when6ElementsFindNotExitThenOptionEmpty() {
        Node<Integer> root = new Node(10);
        Tree<Integer> tree = new Tree<>(root);
        tree.add(10, 11);
        Iterator it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(11));
        assertThat(it.hasNext(), is(false));
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void whenTreeIsBinaryThenReturnTrue() {
        Node<Integer> root = new Node(1);
        Tree<Integer> tree = new Tree<>(root);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        Iterator it = tree.iterator();
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinaryThenReturnFalse() {
        Node<Integer> root = new Node(1);
        Tree<Integer> tree = new Tree<>(root);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        Iterator it = tree.iterator();
        assertThat(tree.isBinary(), is(false));
    }
}
