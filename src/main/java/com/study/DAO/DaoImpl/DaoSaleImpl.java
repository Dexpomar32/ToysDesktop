package com.study.DAO.DaoImpl;

import com.study.DAO.DaoModels.DaoSale;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.Sale;
import com.study.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoSaleImpl implements DaoSale {
    private final static String HQL_SELECT_ALL_FROM_VANZARI = "FROM Sale";
    private final static String HQL_SELECT_VANZARI_ZI = "SELECT SUM(v.quantity) FROM Sale v WHERE v.saleDate = :data";
    private final SessionFactory sessionFactory;

    public DaoSaleImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    @Override
    public List<Sale> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Sale> query = session.createQuery(HQL_SELECT_ALL_FROM_VANZARI, Sale.class);
            return query.list();
        }
    }

    @Override
    public Sale findByCod(String cod) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Sale.class)
                    .load(cod);
        }
    }

    @Override
    public void update(String cod, Sale entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Sale sale = session.bySimpleNaturalId(Sale.class)
                    .load(cod);

            try {
                sale.setToy(entity.getToy());
                sale.setSaleDate(entity.getSaleDate());
                sale.setQuantity(entity.getQuantity());
                session.merge(sale);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String cod) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Sale sale = session.bySimpleNaturalId(Sale.class)
                    .load(cod);
            session.beginTransaction();
            session.evict(sale);
            session.remove(sale);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Sale entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sales(java.sql.Date data) {
        Long count;

        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(HQL_SELECT_VANZARI_ZI, Long.class);
            count = query.setParameter("data", data).getSingleResult();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        return "Total number of sales on date " + data + " is: " + count;
    }
}
