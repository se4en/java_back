package DAO;

import entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class PaymentDAO {

    public Payment findById(long id) {
        try {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Payment.class, id);
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }

    public boolean save(Payment payment) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(payment);
            tx1.commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean update(Payment payment) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(payment);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean delete(Payment payment) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(payment);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public List<Payment> findAll() {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            CriteriaQuery<Payment> criteria = session.getCriteriaBuilder().createQuery(Payment.class);
            criteria.from(Payment.class);
            List<Payment> payments = session.createQuery(criteria).getResultList();
            session.close();
            return payments;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }
}
