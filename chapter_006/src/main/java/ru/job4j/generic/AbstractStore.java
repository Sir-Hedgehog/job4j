package ru.job4j.generic;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 26.02.2019
 */

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> base = new SimpleArray<>(3);

    @Override
    public void add(T model) {
        base.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            base.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            base.remove(index);
            result = true;
        }
        return result;
    }


    @Override
    public T findById(String id) {
        T result = null;
        int index = this.findIndex(id);
        if (index > -1) {
            result = base.get(index);
        }
        return result;
    }

    /**
     * Метод находит индекс элемента списка по идентификатору
     * @param id вводится идентификатор
     * @return индекс элемента списка по идентификатору
     */
    private int findIndex(String id) {
        int number = 0;
        for (int index = -1; !base.iterator().hasNext(); index++) {
            if (base.get(index).getId().equals(id)) {
                number = index;
            }
        }
        return number;
    }
}
