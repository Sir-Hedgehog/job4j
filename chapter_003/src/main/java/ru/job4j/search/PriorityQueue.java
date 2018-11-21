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
        if (this.tasks.size() > 1) {
            for (int index = 1; index < this.tasks.size(); index++) {
                if (this.tasks.get(index - 1).getPriority() > task.getPriority()) {
                    this.tasks.add(index - 1, task);
                    this.tasks.removeLast();
                    break;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
