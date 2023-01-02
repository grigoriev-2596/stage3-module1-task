package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    T create(T o);

    List<T> readAll();

    T readById(Long id);

    T update(T o);

    Boolean delete(Long id);
}
