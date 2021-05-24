package DAO;

import entities.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class ProjectDAO {

    public Project findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    public void save(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(project);
        tx1.commit();
        session.close();
    }

    public void update(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(project);
        tx1.commit();
        session.close();
    }

    public void delete(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(project);
        tx1.commit();
        session.close();
    }

    public List<Project> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<Project> criteria = session.getCriteriaBuilder().createQuery(Project.class);
        criteria.from(Project.class);
        List<Project> projects = session.createQuery(criteria).getResultList();
        session.close();
        return projects;
    }
}
