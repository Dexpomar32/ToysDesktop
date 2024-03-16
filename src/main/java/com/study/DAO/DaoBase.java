package com.study.DAO;

import com.study.DAO.Exceptions.DaoException;

import java.util.List;

public interface DaoBase<K, T> {
    List<T> findAll() throws DaoException;
    T findByCod(K cod) throws DaoException;
    void update(K cod, T entity) throws DaoException;
    void delete(K cod) throws DaoException;
    void insert(T entity) throws DaoException;
}