package ru.job4j.tree;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.03.2019
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;
    private Queue<Node<E>> queue = new LinkedList<>();

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> top = new Node<>(parent);
        Node<E> low = new Node<>(child);
        if (this.findBy(parent).isPresent() && !this.findBy(child).isPresent()) {
            top.add(low);
            result = true;
            modCount++;
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
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Queue<Node<E>> queue = new LinkedList<>();

            @Override
            public boolean hasNext() {
                queue.offer(root);
                return queue != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = queue.poll();
                for (Node<E> child : result.leaves()) {
                    queue.offer(child);
                }
                return result.getValue();
            }
        };
    }
}
