package DAO;

import entities.Worker;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class WorkerDAO {

    public Worker findById(long id) {
        try {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Worker.class, id);
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }

    public boolean save(Worker worker) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(worker);
            tx1.commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean update(Worker worker) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(worker);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean delete(Worker worker) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(worker);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public List<Worker> findAll() {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            CriteriaQuery<Worker> criteria = session.getCriteriaBuilder().createQuery(Worker.class);
            criteria.from(Worker.class);
            List<Worker> workers = session.createQuery(criteria).getResultList();
            session.close();
            return workers;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }
}
