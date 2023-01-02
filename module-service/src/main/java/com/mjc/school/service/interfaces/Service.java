package com.mjc.school.service.interfaces;

import java.util.List;

public interface Service<T1, T2> {
    T2 create(T1 o);

    List<T2> getAll();

    T2 getById(long id);

    T2 update(T1 o);

    boolean delete(long id);
}
