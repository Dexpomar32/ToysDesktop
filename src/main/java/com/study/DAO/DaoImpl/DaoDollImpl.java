package com.study.DAO.DaoImpl;

import com.study.DAO.DaoModels.DaoDoll;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.Doll;
import com.study.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoDollImpl implements DaoDoll {
    private final static String HQL_SELECT_ALL_FROM_PAPUSILE = "FROM Doll";
    private final static String HQL_PAPUSI_ASC = "FROM Doll p JOIN FETCH p.toy j ORDER BY j.price ASC";
    private final SessionFactory sessionFactory;

    public DaoDollImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Doll> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Doll> query = session.createQuery(HQL_SELECT_ALL_FROM_PAPUSILE, Doll.class);
            return query.list();
        }
    }

    @Override
    public Doll findByCod(String cod) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Doll.class)
                    .load(cod);
        }
    }

    @Override
    public void update(String cod, Doll entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Doll doll = session.bySimpleNaturalId(Doll.class)
                    .load(cod);

            try {
                doll.setToy(entity.getToy());
                doll.setMaterial(entity.getMaterial());
                doll.setHeight(entity.getHeight());
                session.merge(doll);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String cod) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Doll doll = session.bySimpleNaturalId(Doll.class)
                    .load(cod);
            session.beginTransaction();
            session.evict(doll);
            session.remove(doll);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Doll entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doll> ascending() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query<Doll> query = session.createQuery(HQL_PAPUSI_ASC, Doll.class);
        List<Doll> dollList = query.list();

        session.getTransaction().commit();
        return dollList;
    }
}
