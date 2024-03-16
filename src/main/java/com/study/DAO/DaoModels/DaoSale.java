package com.study.DAO.DaoModels;

import com.study.DAO.DaoBase;
import com.study.Model.Sale;

import java.sql.Date;

public interface DaoSale extends DaoBase<String, Sale> {
    String sales(Date data);
}
