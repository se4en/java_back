package DAO;

import entities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class RoleDAO {

    public Role findById(long id) {
        try {
            Role kek = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, id);
            System.out.println(kek);
            return kek;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }

    public boolean save(Role role) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(role);
            tx1.commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean update(Role role) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(role);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean delete(Role role) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(role);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public List<Role> findAll() {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            CriteriaQuery<Role> criteria = session.getCriteriaBuilder().createQuery(Role.class);
            criteria.from(Role.class);
            List<Role> roles = session.createQuery(criteria).getResultList();
            session.close();
            return roles;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }
}
