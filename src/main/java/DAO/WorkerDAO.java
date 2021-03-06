package DAO;

import entities.Worker;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class WorkerDAO {

    public Worker findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Worker.class, id);
    }

    public void save(Worker worker) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(worker);
        tx1.commit();
        session.close();
    }

    public void update(Worker worker) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(worker);
        tx1.commit();
        session.close();
    }

    public void delete(Worker worker) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(worker);
        tx1.commit();
        session.close();
    }

    public List<Worker> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Worker> criteria = session.getCriteriaBuilder().createQuery(Worker.class);
        criteria.from(Worker.class);
        List<Worker> workers = session.createQuery(criteria).getResultList();
        session.close();
        return workers;
    }
}
