package services;

import DAO.RoleDAO;
import entities.Project;
import entities.Role;
import entities.Worker;
import java.util.Objects;

import java.util.List;

public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();

    public Role findRole(long id) {
        return roleDAO.findById(id);
    }

    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    public void deleteRole(Role role) {
        roleDAO.delete(role);
    }

    public boolean deleteRoleById(long id) {
        Role role = roleDAO.findById(id);
        if (role == null) {
            return  false;
        }
        else {
            roleDAO.delete(role);
            return true;
        }
    }

    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }

    public List<Role> findRolesByWorker(Worker worker) {
        List<Role> roles = findAllRoles();
        roles.removeIf(role -> (!Objects.equals(role.getWorker().getId(), worker.getId())));
        return roles;
    }

    public List<Role> findRolesByProject(Project project) {
        List<Role> roles = findAllRoles();
        roles.removeIf(role -> (!Objects.equals(role.getProject().getId(), project.getId())));
        return roles;
    }

    /*
    Close all roles in project
     */
    public boolean closeRolesByProject(Project project, java.sql.Timestamp ts) {
        List<Role> roles = findRolesByProject(project);
        for (Role role : roles) {
            if (role.getEnd_date()==null) {
                role.setEnd_date(ts);
                if (!updateRole(role)) {
                    return false;
                }
            }
        }
        return true;
    }

}
