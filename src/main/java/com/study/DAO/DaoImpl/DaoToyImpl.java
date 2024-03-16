package com.study.DAO.DaoImpl;

import com.study.DAO.DaoModels.DaoToy;
import com.study.Model.PopularProduct;
import com.study.Model.Toy;
import com.study.Util.HibernateUtil;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DaoToyImpl implements DaoToy {
    private final static String HQL_SELECT_ALL_FROM_JUCARII = "FROM Toy";
    private static final String HQL_MOVE_TOYS_WITH_ZERO_QUANTITY = "Exclude";
    private static final String HQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY = "SELECT j FROM Toy j WHERE j.price = " +
            "(SELECT MAX(p.price) FROM Toy p) OR j.price = (SELECT MIN(p.price) FROM Toy p)";
    private static final String HQL_SELECT_TOYS_AVG_DUPA_TARA = "SELECT AVG(j.price) FROM Toy j WHERE j.country = :tara";
    private static final String HQL_INSERT_JUCARIE_MONDOVA = "InsertMoldova";
    private static final String HQL_SELECT_TOYS_WITH_FILTERS = "FROM Toy j " +
            "WHERE j.price <= :maxPret AND j.minimAge >= :minVarsta AND j.minimAge <= :maxVarsta";
    private static final String POPULAR_PRODUCTS_QUERY =
            "SELECT NEW com.study.Model.PopularProduct(" +
                    "j.cod, j.name, j.price, j.quantity, j.country, j.minimAge, j.category, SUM(v.quantity)) " +
                    "FROM Toy j JOIN Sale v ON j.id = v.toy.id " +
                    "GROUP BY j.id, j.cod, j.name, j.price, j.quantity, j.country, j.minimAge, j.category " +
                    "ORDER BY SUM(v.quantity) DESC";
    private static final String HQL_EXCLUDE = "SELECT j FROM Toy j WHERE j.price = (SELECT MAX(p.price) " +
            "FROM Toy p) OR j.price = (SELECT MIN(p.price) FROM Toy p)";
    private static final String HQL_INSERT_MOLDOVA = "SELECT j FROM Toy j WHERE j.country = 'Moldova'";
    private final SessionFactory sessionFactory;

    public DaoToyImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Toy> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Toy> query = session.createQuery(HQL_SELECT_ALL_FROM_JUCARII, Toy.class);
            return query.list();
        }
    }

    @Override
    public Toy findByCod(String cod) {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Toy.class)
                    .load(cod);
        }
    }

    @Override
    public void update(String cod, Toy entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Toy toy = session.bySimpleNaturalId(Toy.class)
                    .load(cod);

            try {
                toy.setName(entity.getName());
                toy.setPrice(entity.getPrice());
                toy.setQuantity(entity.getQuantity());
                toy.setCountry(entity.getCountry());
                toy.setMinimAge(entity.getMinimAge());
                toy.setCategory(entity.getCategory());
                session.merge(toy);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String cod) {
        try (Session session = sessionFactory.openSession()) {
            Toy toy = session.bySimpleNaturalId(Toy.class)
                    .load(cod);
            session.beginTransaction();
            session.evict(toy);
            session.remove(toy);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Toy entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exclude() {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery query = session.createNamedStoredProcedureQuery(HQL_MOVE_TOYS_WITH_ZERO_QUANTITY);
            query.execute();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Toy> expensiveCheap() {
        try (Session session = sessionFactory.openSession()) {
            Query<Toy> query = session.createQuery(HQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY, Toy.class);
            System.out.println(query.list());
            return query.list();
        }
    }

    @Override
    public String avgPrice(String entity) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery(HQL_SELECT_TOYS_AVG_DUPA_TARA, Double.class);
            query.setParameter("tara", entity);
            Double avgPret = query.uniqueResult();

            if (avgPret != null) {
                return "Average price in country " + entity + " is: " + avgPret;
            } else {
                return "Not found toys created in country: " + entity;
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertMoldova() {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery query = session.createNamedStoredProcedureQuery(HQL_INSERT_JUCARIE_MONDOVA);
            query.execute();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Toy> filters(double x, int n1, int n2) {
        try (Session session = sessionFactory.openSession()) {
            Query<Toy> query = session.createQuery(HQL_SELECT_TOYS_WITH_FILTERS, Toy.class);
            query.setParameter("maxPret", x);
            query.setParameter("minVarsta", n1);
            query.setParameter("maxVarsta", n2);
            return query.list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String popular() {
        try (Session session = sessionFactory.openSession()) {
            Query<PopularProduct> query = session.createQuery(POPULAR_PRODUCTS_QUERY, PopularProduct.class);
            List<PopularProduct> popularProducts = query.getResultList();

            if (!popularProducts.isEmpty()) {
                PopularProduct popularProduct = popularProducts.get(0);
                return "Toy name: " + popularProduct.getName() +
                        ", Total quantity: " + popularProduct.getQuantity();
            }
        }

        return null;
    }

    @Override
    public List<Toy> findExclude() {
        try (Session session = sessionFactory.openSession()) {
            Query<Toy> query = session.createQuery(HQL_EXCLUDE, Toy.class);
            return query.list();
        }
    }

    @Override
    public List<Toy> findMoldova() {
        try (Session session = sessionFactory.openSession()) {
            Query<Toy> query = session.createQuery(HQL_INSERT_MOLDOVA, Toy.class);
            return query.list();
        }
    }
}
