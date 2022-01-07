package ru.bloking.api.user;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UraniumSqlUtility {

    static SessionFactory sessionFactory = build();
    static Map<String, Session> sessionMap = new HashMap<>();

    public void setSession(final String name,
                           final Session session) {
        sessionMap.put(name, session);
    }

    public Session getSession(String name) {
        return sessionMap.get(name);
    }

    private SessionFactory build() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutDown() {
        getSessionFactory().close();
    }
}
