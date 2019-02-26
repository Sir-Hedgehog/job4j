package ru.job4j.generic;

public class RoleStore<T extends Role> implements Store<T> {
    private SimpleArray<T> role = new SimpleArray<>(3);

    @Override
    public void add(T model) {
        role.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index).getId().equals(id)) {
                role.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index).getId().equals(id)) {
                role.remove(index);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index).getId().equals(id)) {
                result = role.get(index);
                break;
            }
        }
        return result;
    }
}
