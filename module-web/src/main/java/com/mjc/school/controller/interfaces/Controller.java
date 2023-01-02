package com.mjc.school.controller.interfaces;

import java.util.List;

public interface Controller<T1, T2> {
    T2 create(T1 o);

    List<T2> readAll();

    T2 readById(Long id);

    T2 update(T1 o);

    Boolean delete(Long id);
}
