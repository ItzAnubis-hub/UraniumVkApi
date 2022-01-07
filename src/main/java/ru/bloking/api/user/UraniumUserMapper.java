package ru.bloking.api.user;

import api.longpoll.bots.model.objects.basic.Message;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class UraniumUserMapper {
    final static Map<Integer, UraniumUser> URANIUM_USER_MAP = new HashMap<>();
    final static List<UraniumUser> URANIUM_USER_LIST = new ArrayList<>();
    final static SessionFactory sessionFactory = UraniumSqlUtility.getSessionFactory();

    static Session session = sessionFactory.openSession();
    static Transaction transaction = session.beginTransaction();

    private void openSession() {
        if (session != null) session.close();

        session = sessionFactory.openSession();
    }

    private void save(final UraniumUser uraniumUser) {

            openSession();
            transaction = session.beginTransaction();

            session.save(uraniumUser);

            commitChanges();

    }

    private void commitChanges() {
        try {
            session.flush();
            session.clear();

            transaction.commit();
            session.close();

            mapAllUsers();
        } catch(Exception ex) {
            return;
        }
    }

    private void mapAllUsers() {
        if (!URANIUM_USER_MAP.isEmpty()) URANIUM_USER_MAP.clear();
        getAllUsers().forEach(uraniumUser -> URANIUM_USER_MAP.put(uraniumUser.getId(), uraniumUser));

        if (!URANIUM_USER_LIST.isEmpty()) URANIUM_USER_LIST.clear();
        URANIUM_USER_LIST.addAll(getAllUsers());

        System.out.println(URANIUM_USER_MAP);
        System.out.println(URANIUM_USER_LIST);
    }

    public void create(final Message message) {
        openSession();
        transaction = session.beginTransaction();

        if (URANIUM_USER_MAP.get(message.getFromId()) != null) return;

        save(new UraniumUser(
                message.getFromId(), "test", "Не указан", 0
        ));

        commitChanges();
    }

    public void updateUsers() {
        if (session.isOpen()) {
            session.close();
            openSession();
            mapAllUsers();
            return;
        }
    }


    public boolean isExist(final int id) {
        return URANIUM_USER_LIST
                .stream()
                .map(UraniumUser::getId)
                .anyMatch(idf -> idf == id);
    }

    @SneakyThrows
    private List<UraniumUser> getAllUsers() {
        openSession();

        val builder = session.getCriteriaBuilder();
        CriteriaQuery<UraniumUser> criteriaQuery = builder.createQuery(UraniumUser.class);
        Root<UraniumUser> root = criteriaQuery.from(UraniumUser.class);
        criteriaQuery.select(root);
        Query<UraniumUser> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public UraniumUser getUraniumUserById(final int id) {
        return URANIUM_USER_MAP.get(id);
    }
}

