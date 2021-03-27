package DAO;

import entities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class RoleDAO {

    public Role findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, id);
    }

    public void save(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(role);
        tx1.commit();
        session.close();
    }

    public void update(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(role);
        tx1.commit();
        session.close();
    }

    public void delete(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(role);
        tx1.commit();
        session.close();
    }

    public List<Role> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Role> criteria = session.getCriteriaBuilder().createQuery(Role.class);
        criteria.from(Role.class);
        List<Role> roles = session.createQuery(criteria).getResultList();
        session.close();
        return roles;
    }
}

