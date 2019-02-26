package ru.job4j.generic;

public class UserStore<E extends User> implements Store<E> {
    private SimpleArray<E> user = new SimpleArray<>(3);

    @Override
    public void add(E model) {
        user.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        for (int index = 0; !user.iterator().hasNext(); index++) {
            if (user.get(index).getId().equals(id)) {
                user.set(index, model);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; !user.iterator().hasNext(); index++) {
            if (user.get(index).getId().equals(id)) {
                user.remove(index);
                result = true;
            }
        }
        return result;
    }

    @Override
    public E findById(String id) {
        E result = null;
        for (int index = 0; !user.iterator().hasNext(); index++) {
            if (user.get(index).getId().equals(id)) {
                result = user.get(index);
            }
        }
        return result;
    }
}

