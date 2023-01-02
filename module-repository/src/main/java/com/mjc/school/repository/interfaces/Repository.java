package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    T create(T o);

    List<T> getAll();

    T getById(long id);

    T update(T o);

    boolean delete(long id);
}
