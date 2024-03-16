package com.study.Util;

import com.study.Config.HibernateConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class HibernateUtil {
    @Getter
    private static final HibernateUtil instance = new HibernateUtil();
    @Getter
    private final SessionFactory sessionFactory;

    private HibernateUtil() {
        sessionFactory = new HibernateConfig().buildSessionFactory();
    }
}