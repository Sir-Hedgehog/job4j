package ru.job4j.generic;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 26.02.2019
 */

public class RoleStore<T extends Role> implements Store<T> {
    private SimpleArray<T> role = new SimpleArray<>(3);

    @Override
    public void add(T model) {
        role.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            role.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index > -1) {
            role.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = this.findIndex(id);
        if (index > -1) {
            result = role.get(index);
        }
        return result;
    }

    private int findIndex(String id) {
        int index = 0;
        while (!role.iterator().hasNext()) {
            if (role.get(index).getId().equals(id)) {
                break;
            }
            role.iterator().next();
        }
        return index;
    }
}
