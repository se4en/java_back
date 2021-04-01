package DAO;

import entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class PaymentDAO {

    public Payment findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Payment.class, id);
    }

    public void save(Payment payment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(payment);
        tx1.commit();
        session.close();
    }

    public void update(Payment payment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(payment);
        tx1.commit();
        session.close();
    }

    public void delete(Payment payment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(payment);
        tx1.commit();
        session.close();
    }

    public List<Payment> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Payment> criteria = session.getCriteriaBuilder().createQuery(Payment.class);
        criteria.from(Payment.class);
        List<Payment> payments = session.createQuery(criteria).getResultList();
        session.close();
        return payments;
    }
}
