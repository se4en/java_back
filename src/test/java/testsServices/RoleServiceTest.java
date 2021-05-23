package testsServices;

import entities.Role;
import entities.Worker;
import entities.Project;
import services.WorkerService;
import services.RoleService;
import services.ProjectService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RoleServiceTest {
    public WorkerService workerService;
    public ProjectService projectService;
    public Worker worker_1, worker_2, worker_3;
    public Project project_1, project_2, project_3;

    @BeforeClass
    public void prepForTests() {
        workerService = new WorkerService();
        projectService = new ProjectService();

        worker_1 = new Worker(
                "Fr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );
        worker_2 = new Worker(
                "Gr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );
        worker_3 = new Worker(
                "Hr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );
        workerService.saveWorker(worker_1);
        workerService.saveWorker(worker_2);
        workerService.saveWorker(worker_3);

        project_1 = new Project(
                "project-1", "description", Date.valueOf("2011-10-02 18:48:05"),
                Date.valueOf("2011-10-02 18:48:05"), "active"
        );
        project_2 = new Project(
                "project-2", "description", Date.valueOf("2011-10-02 18:48:05"),
                Date.valueOf("2011-10-02 18:48:05"), "active"
        );
        project_3 = new Project(
                "project-3", "description", Date.valueOf("2011-10-02 18:48:05"),
                Date.valueOf("2011-10-02 18:48:05"), "active"
        );
        projectService.saveProject(project_1);
        projectService.saveProject(project_2);
        projectService.saveProject(project_3);
    }

    @AfterClass
    public void cleanAfterTests() {
        workerService.deleteWorker(worker_1);
        workerService.deleteWorker(worker_2);
        workerService.deleteWorker(worker_3);
        projectService.deleteProject(project_1);
        projectService.deleteProject(project_2);
        projectService.deleteProject(project_3);
    }

    @Test
    public void testSaveFindRole() {
        RoleService service = new RoleService();
        Role role = new Role(
                worker_1, project_1, "role-1", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );

        // test save-find
        service.saveRole(role);
        Role service_role = service.findRole(role.getId());
        Assert.assertEquals(service_role, role);
        service.deleteRoleById(service_role.getId());
        // test empty find
        Assert.assertNull(service.findRole(role.getId()));
    }

    @Test
    public void testDeleteRole() {
        RoleService service = new RoleService();
        Role role = new Role(
                worker_1, project_1, "role-4", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );

        // test delete by id
        service.saveRole(role);
        service.deleteRoleById(role.getId());
        Assert.assertEquals(service.findRole(role.getId()), null);
        // test delete
        service.saveRole(role);
        service.deleteRole(role);
        Assert.assertEquals(service.findRole(role.getId()), null);
    }

    @Test
    public void testUpdateRole() {
        RoleService service = new RoleService();
        Role role = new Role(
                worker_1, project_1, "role-1", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );

        // test save-update
        service.saveRole(role);
        Role service_role = service.findRole(role.getId());
        service_role.setRole("role-2");
        service.updateRole(service_role);
        service_role = service.findRole(service_role.getId());
        Assert.assertEquals(service.findRole(service_role.getId()).getRole(), "role-2");
        service.deleteRoleById(service_role.getId());
    }


    @Test
    public void testFindAllRoles() {
        RoleService service = new RoleService();
        Role role_1 = new Role(
                worker_1, project_1, "role-1", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        Role role_2 = new Role(
                worker_2, project_2, "role-2", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        Role role_3 = new Role(
                worker_3, project_3, "role-3", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        service.saveRole(role_1);
        service.saveRole(role_2);
        service.saveRole(role_3);
        List<Role> roles = service.findAllRoles();
        int prev_size = roles.size();
        roles.remove(role_1);
        roles.remove(role_2);
        roles.remove(role_3);
        int new_size = roles.size();
        Assert.assertEquals(new_size, prev_size-3);
        service.deleteRole(role_1);
        service.deleteRole(role_2);
        service.deleteRole(role_3);
    }

    @Test
    public void testFindRolesByWorker() {
        RoleService service = new RoleService();
        Role role_1 = new Role(
                worker_1, project_1, "role-3", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        Role role_2 = new Role(
                worker_1, project_2, "role-4", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        service.saveRole(role_1);
        service.saveRole(role_2);
        List<Role> roles = service.findRolesByWorker(worker_1);
        int prev_size = roles.size();
        roles.remove(role_1);
        roles.remove(role_2);
        int new_size = roles.size();
        Assert.assertEquals(new_size, prev_size-2);
        service.deleteRole(role_1);
        service.deleteRole(role_2);
    }

    @Test
    public void testFindRolesByProject() {
        RoleService service = new RoleService();
        Role role_1 = new Role(
                worker_1, project_2, "role-5", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        Role role_2 = new Role(
                worker_2, project_2, "role-6", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 19:48:05")
        );
        service.saveRole(role_1);
        service.saveRole(role_2);
        List<Role> roles = service.findRolesByProject(project_2);
        int prev_size = roles.size();
        roles.remove(role_1);
        roles.remove(role_2);
        int new_size = roles.size();
        Assert.assertEquals(new_size, prev_size-2);
        service.deleteRole(role_1);
        service.deleteRole(role_2);
    }

    @Test
    public void testCloseRolesByProject() {
        RoleService service = new RoleService();
        Role role_1 = new Role(
                worker_1, project_2, "role-5", "description",
                Date.valueOf("2011-10-02 18:48:05"), null
        );
        Role role_2 = new Role(
                worker_2, project_3, "role-6", "description",
                Date.valueOf("2011-10-02 18:48:05"), Date.valueOf("2011-10-02 18:47:05")
        );
        service.saveRole(role_1);
        service.saveRole(role_2);
        // test right close
        Assert.assertTrue(service.closeRolesByProject(project_2, Date.valueOf("2011-10-02 18:48:05")));
        // test fake close
        Assert.assertFalse(service.closeRolesByProject(project_3, Date.valueOf("2011-10-02 18:48:05")));
        // test role after right close
        Assert.assertEquals(service.findRole(role_1.getId()).getEnd_date(), Date.valueOf("2011-10-02 18:48:05"));
        // test role after fake close
        Assert.assertEquals(service.findRole(role_2.getId()).getEnd_date(), Date.valueOf("2011-10-02 18:47:05"));
        service.deleteRole(role_1);
        service.deleteRole(role_2);
    }

}