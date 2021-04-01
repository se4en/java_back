package services;

import DAO.ProjectDAO;
import entities.Project;
import entities.Worker;
import entities.Role;

import java.sql.Timestamp;
import java.util.*;

public class ProjectService {

    private ProjectDAO projectDAO = new ProjectDAO();

    public Project findProject(long id) {
        return projectDAO.findById(id);
    }

    public void saveProject(Project project) {
        projectDAO.save(project);
    }

    public void deleteProject(Project project) {
        projectDAO.delete(project);
    }

    public boolean deleteProjectById(long id) {
        Project project = projectDAO.findById(id);
        if (project == null) {
            return false;
        }
        else {
            projectDAO.delete(project);
            return true;
        }
    }

    public void updateProject(Project project) {
        projectDAO.update(project);
    }

    public List<Project> findAllProjects() {
        return projectDAO.findAll();
    }

    /*
     Return all projects with specific status
     */
    public List<Project> findProjectsByStatus(String status) {
        List<Project> projects = findAllProjects();
        projects.removeIf(project -> (!Objects.equals(project.getStatus(), status)));
        return projects;
    }

    /*
     Return all projects in which worker participated
     */
    public List<Project> findProjectsByWorker(RoleService roleService, Worker worker) {
        List<Role> roles = roleService.findRolesByWorker(worker);
        HashSet<Project> projects = new HashSet<>();
        for (Role role : roles)
        {
            System.out.println(role.getProject());
            projects.add(role.getProject());
        }
        return new ArrayList<>(projects);
    }

    /*
     Return all projects in which worker participated in a time period
     */
    public List<Project> findProjectsByWorkerInPeriod(RoleService roleService, Worker worker, java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Project> projects = findProjectsByWorker(roleService, worker);
        projects.removeIf(project -> (project.getStart_date()!=null && project.getEnd_date()!=null && (project.getStart_date().before(start_date) | project.getEnd_date().after(end_date))));
        return projects;
    }

    /*
     Return all projects in a time period
     */
    public List<Project> findProjectsInPeriod(java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Project> projects = findAllProjects();
        projects.removeIf(project -> (project.getStart_date()!=null && project.getEnd_date()!=null && (project.getStart_date().before(start_date) | project.getEnd_date().after(end_date))));
        return projects;
    }

    /*
     Close project and and roles in it
     */
    public boolean closeProject(RoleService roleService, Project project) {
        if (roleService==null | project==null) {
            return false;
        }
        // close project
        // get current timestamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        if (project.getEnd_date()==null) {
            project.setEnd_date(ts);
        }
        if (!project.getStatus().equals("Closed")) {
            project.setStatus("Closed");
        }
        projectDAO.update(project);
        // close roles
        return roleService.closeRolesByProject(project, ts);
    }

}
