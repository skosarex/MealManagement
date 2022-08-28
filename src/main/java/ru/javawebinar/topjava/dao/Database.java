package ru.javawebinar.topjava.dao;

import java.io.IOException;
import java.util.List;

public interface Database<T> {
    void add(T elem) throws IOException;

    T get(int id) throws IOException;

    List<T> getAll() throws IOException;

    void delete(int id) throws IOException;

    void update(T elem) throws IOException;
}
