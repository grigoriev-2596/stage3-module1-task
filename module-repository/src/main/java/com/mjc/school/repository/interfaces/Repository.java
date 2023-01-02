package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    T create(T o);

    List<T> readAll();

    T readById(long id);

    T update(T o);

    Boolean delete(long id);
}
