package ru.job4j.generic;

public class RoleStore<Role> implements Store {
    private SimpleArray<Role> role = new SimpleArray<>(3);

    @Override
    public void add(Base model) {
        role.add((Role) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index) == id) {
                role.set(index, (Role) model);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index) == id) {
                role.remove(index);
                result = true;
            }
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        Base result = null;
        for (int index = 0; !role.iterator().hasNext(); index++) {
            if (role.get(index) == id) {
                result = (Base) role.get(index);
            }
        }
        return result;
    }
}
