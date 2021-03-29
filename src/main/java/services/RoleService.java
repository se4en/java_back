package services;

import DAO.RoleDAO;
import entities.Project;
import entities.Role;
import entities.Worker;

import java.util.List;

public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();

    public Role findRole(int id) {
        return roleDAO.findById(id);
    }

    public boolean saveRole(Role role) {
        return roleDAO.save(role);
    }

    public boolean deleteRole(Role role) {
        return roleDAO.delete(role);
    }

    public boolean deleteRoleById(int id) {
        return roleDAO.delete(roleDAO.findById(id));
    }

    public boolean updateRole(Role role) {
        return roleDAO.update(role);
    }

    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }

    public List<Role> findRolesByWorker(Worker worker) {
        List<Role> roles = findAllRoles();
        roles.removeIf(role -> (role.getWorker() != worker));
        return roles;
    }

    public List<Role> findRolesByProject(Project project) {
        List<Role> roles = findAllRoles();
        roles.removeIf(role -> (role.getProject() != project));
        return roles;
    }

    /*
    Close all roles in project
     */
    public boolean closeRolesByProject(Project project, java.sql.Timestamp ts) {
        List<Role> roles = findRolesByProject(project);
        for (Role role : roles) {
            if (role.getEnd_date()!=null) {
                role.setEnd_date(ts);
                if (!updateRole(role)) {
                    return false;
                }
            }
        }
        return true;
    }

}
