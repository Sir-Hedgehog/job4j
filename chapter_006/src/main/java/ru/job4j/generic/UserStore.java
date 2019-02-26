package ru.job4j.generic;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 26.02.2019
 */

public class UserStore<E extends User> implements Store<E> {
    private SimpleArray<E> user = new SimpleArray<>(3);

    @Override
    public void add(E model) {
        user.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            user.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            user.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public E findById(String id) {
        E result = null;
        int index = this.findIndex(id);
        if (index > -1) {
            result = user.get(index);
        }
        return result;
    }

    private int findIndex(String id) {
        int index = 0;
        while (!user.iterator().hasNext()) {
            if (user.get(index).getId().equals(id)) {
                break;
            }
            user.iterator().next();
        }
        return index;
    }
}

