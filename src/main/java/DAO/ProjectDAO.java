package DAO;

import entities.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class ProjectDAO {

    public Project findById(int id) {
        try {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Project.class, id);
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }

    public boolean save(Project project) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(project);
            tx1.commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean update(Project project) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(project);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public boolean delete(Project project) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(project);
            tx1.commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return false;
        }
    }

    public List<Project> findAll() {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            CriteriaQuery<Project> criteria = session.getCriteriaBuilder().createQuery(Project.class);
            criteria.from(Project.class);
            List<Project> projects = session.createQuery(criteria).getResultList();
            session.close();
            return projects;
        } catch (Exception e) {
            System.out.println("Exception!" + e);
            return null;
        }
    }
}
