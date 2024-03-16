package com.study.DAO.DaoModels;

import com.study.DAO.DaoBase;
import com.study.Model.Toy;

import java.util.List;

public interface DaoToy extends DaoBase<String, Toy> {
    void exclude();
    List<Toy> expensiveCheap();
    String avgPrice(String entity);
    void insertMoldova();
    List<Toy> filters(double x, int n1, int n2);
    String popular();
    List<Toy> findExclude();
    List<Toy> findMoldova();
}
