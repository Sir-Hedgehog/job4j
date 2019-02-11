package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 11.02.2018
 */

class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять элемент в нужную позицию.
     * Позиция должна определять приоритет по полю .
     * Для вставки используем add(int index, E value)
     * @param task задача
     */
    void put(Task task) {
        var out = 0;
        for (var in = 0; in < this.tasks.size(); in++) {
            if (this.tasks.get(in).getPriority() > task.getPriority()) {
                out = in;
                break;
            }
        }
        this.tasks.add(out, task);
    }

    Task take() {
        return this.tasks.poll();
    }
}
