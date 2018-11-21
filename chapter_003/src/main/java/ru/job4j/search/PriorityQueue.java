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
        this.tasks.add(task);
        for (int index = 0; index < this.tasks.size(); index++) {
            if (this.tasks.get(index).getPriority() > task.getPriority()) {
                this.tasks.add(index, task);
                break;
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
