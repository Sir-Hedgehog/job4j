package ru.job4j.list;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 04.03.2019
 */

public class ConnectedList<T> {

    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
