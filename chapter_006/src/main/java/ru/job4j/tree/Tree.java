package ru.job4j.tree;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.03.2019
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> low = new Node<>(child);
        final Optional<Node<E>> byParent = this.findBy(parent);
        if (byParent.isPresent() && this.findBy(child).isEmpty()) {
            byParent.get().leaves().add(low);
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

    public boolean isBinary() {
        boolean result = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Queue<Node<E>> queue = new LinkedList<>(Collections.singleton(root));

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
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
