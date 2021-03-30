package services;

import DAO.ProjectDAO;
import entities.Project;
import entities.Worker;
import entities.Role;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ProjectService {

    private ProjectDAO projectDAO = new ProjectDAO();

    public Project findProject(int id) {
        return projectDAO.findById(id);
    }

    public boolean saveProject(Project project) {
        return projectDAO.save(project);
    }

    public boolean deleteProject(Project project) {
        return projectDAO.delete(project);
    }

    public boolean deleteProjectById(int id) {
        return projectDAO.delete(projectDAO.findById(id));
    }

    public boolean updateProject(Project project) {
        return projectDAO.update(project);
    }

    public List<Project> findAllProjects() {
        return projectDAO.findAll();
    }

    /*
     Return all projects with specific status
     */
    public List<Project> findProjectsByStatus(String status) {
        List<Project> projects = findAllProjects();
        projects.removeIf(project -> (project.getStatus() != status));
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
            projects.add(role.getProject());
        }
        return new ArrayList<>(projects);
    }

    /*
     Return all projects in which worker participated in a time period
     */
    public List<Project> findProjectsByWorkerInPeriod(RoleService roleService, Worker worker, java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Project> projects = findProjectsByWorker(roleService, worker);
        projects.removeIf(project -> (project.getStart_date().after(start_date) & project.getEnd_date().before(end_date)));
        return projects;
    }

    /*
     Return all projects in a time period
     */
    public List<Project> findProjectsInPeriod(java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Project> projects = findAllProjects();
        projects.removeIf(project -> (project.getStart_date().after(start_date) & project.getEnd_date().before(end_date)));
        return projects;
    }

    /*
     Close project and and roles in it
     */
    public boolean closeProject(RoleService roleService, Project project) {
        if (roleService==null | project==null) {
            return false;
        }
        // get current timestamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        // close project
        if (project.getEnd_date()==null | project.getStatus()!="Closed") {
            project.setStatus("Closed");
            project.setEnd_date(ts);
            if (!projectDAO.update(project)) {
                return false;
            }
        }
        // close roles
        return roleService.closeRolesByProject(project, ts);
    }

}
