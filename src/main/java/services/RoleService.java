package services;

import DAO.RoleDAO;
import entities.Role;

import java.util.List;

public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();

    public Role findRole(int id) {
        return roleDAO.findById(id);
    }

    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    public void deleteRole(Role role) {
        roleDAO.delete(role);
    }

    public void deleteRoleById(int id) {
        roleDAO.delete(roleDAO.findById(id));
    }

    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }

}