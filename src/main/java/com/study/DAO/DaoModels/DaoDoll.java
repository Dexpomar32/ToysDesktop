package com.study.DAO.DaoModels;

import com.study.DAO.DaoBase;
import com.study.Model.Doll;

import java.util.List;

public interface DaoDoll extends DaoBase<String, Doll> {
    List<Doll> ascending();
}
