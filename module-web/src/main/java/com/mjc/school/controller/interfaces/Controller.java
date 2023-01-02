package com.mjc.school.controller.interfaces;

import java.util.List;

public interface Controller<T1, T2> {
    T2 create(T1 o);

    List<T2> getAll();

    T2 getById(long id);

    T2 update(T1 o);

    boolean delete(long id);
}
