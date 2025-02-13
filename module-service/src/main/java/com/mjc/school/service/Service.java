package com.mjc.school.service;

import java.util.List;

public interface Service<T1, T2> {
    T2 create(T1 o);

    List<T2> readAll();

    T2 readById(Long id);

    T2 update(T1 o);

    Boolean delete(Long id);
}
