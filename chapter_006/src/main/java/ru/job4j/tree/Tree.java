package ru.job4j.tree;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.03.2019
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int size = 0;

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        List<Node<E>> data = new ArrayList<>();
        data.add(this.root);
        Node<E> top = new Node<>(parent);
        Node<E> low = new Node<>(child);
        while (!data.isEmpty()) {
            if (top.leaves().contains(low)) {
                result = false;
                break;
            }
            if (this.root == top) {
                this.root.add(low);
                size++;
                break;
            }
            if (this.root != top && root.leaves().contains(top)) {
                top.add(low);
                size++;
                break;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
