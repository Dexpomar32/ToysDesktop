package com.study.DAO.DaoImpl;

import com.study.DAO.DaoModels.DaoCategory;
import com.study.Model.Category;
import com.study.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoCategoryImpl implements DaoCategory {
    private final static String HQL_SELECT_ALL_FROM_CATEGORII = "FROM Category";
    private final SessionFactory sessionFactory;

    public DaoCategoryImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Category> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Category> query = session.createQuery(HQL_SELECT_ALL_FROM_CATEGORII, Category.class);
            return query.list();
        }
    }

    @Override
    public Category findByCod(String cod) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Category.class)
                    .load(cod);
        }
    }

    @Override
    public void update(String cod, Category entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.bySimpleNaturalId(Category.class)
                    .load(cod);

            try {
                category.setName(entity.getName());
                session.merge(category);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String cod) {
        try (Session session = sessionFactory.openSession()) {
            Category category = session.bySimpleNaturalId(Category.class)
                    .load(cod);
            session.beginTransaction();
            session.evict(category);
            session.remove(category);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Category entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}