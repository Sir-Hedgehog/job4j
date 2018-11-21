package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 21.11.2018
 */

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять элемент в нужную позицию.
     * Позиция должна определять приоритет по полю .
     * Для вставки используем add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int out = 0;
        for (int in = 0; in < this.tasks.size(); in++) {
            if (this.tasks.get(in).getPriority() > task.getPriority()) {
                out = in;
                break;
            }
        }
        this.tasks.add(out, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
