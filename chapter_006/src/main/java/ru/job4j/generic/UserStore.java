package ru.job4j.generic;

public class UserStore<User> implements Store {
    private SimpleArray<User> user = new SimpleArray<>(3);

    @Override
    public void add(Base model) {
        user.add((User) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        int index = 0;
        while (user.iterator().hasNext()) {
            if (user.get(index) == id) {
                user.set(index, (User) model);
                result = true;
            } else {
                index++;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        int index = 0;
        boolean result = false;
        while (user.iterator().hasNext()) {
            if (user.get(index) == id) {
                user.remove(index);
                result = true;
            } else {
                index++;
            }
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        int index = 0;
        Base result = null;
        while (user.iterator().hasNext()) {
            if (user.get(index) == id) {
                result = (Base) user.get(index);
            } else {
                index++;
            }
        }
        return result;
    }
}

