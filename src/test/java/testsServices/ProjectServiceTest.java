package testsServices;

import entities.Project;
import entities.Role;
import entities.Worker;
import services.WorkerService;
import services.ProjectService;
import services.RoleService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ProjectServiceTest {
    public WorkerService workerService;
    public RoleService roleService;
    public Worker worker_1, worker_2, worker_3;

    @BeforeClass
    public void prepForTests() {
        roleService = new RoleService();
        workerService = new WorkerService();

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
    }

    @AfterClass
    public void cleanAfterTests() {
        workerService.deleteWorker(worker_1);
        workerService.deleteWorker(worker_2);
        workerService.deleteWorker(worker_3);
    }

    @Test
    public void testSaveFindProject() {
        ProjectService service = new ProjectService();
        Project project = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );

        // test save-find
        service.saveProject(project);
        Project service_project = service.findProject(project.getId());
        Assert.assertEquals(service_project, project);
        service.deleteProjectById(service_project.getId());
        // test empty find
        Assert.assertNull(service.findProject(project.getId()));
    }

    @Test
    public void testDeleteProject() {
        ProjectService service = new ProjectService();
        Project project = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );

        // test delete by id
        service.saveProject(project);
        service.deleteProjectById(project.getId());
        Assert.assertNull(service.findProject(project.getId()));
        // test delete
        service.saveProject(project);
        service.deleteProject(project);
        Assert.assertNull(service.findProject(project.getId()));
        // test empty delete
        service.deleteProject(project);
        Assert.assertNull(service.findProject(project.getId()));
    }

    @Test
    public void testUpdateProject() {
        ProjectService service = new ProjectService();
        Project project = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );

        // test save-update
        service.saveProject(project);
        Project service_project = service.findProject(project.getId());
        service_project.setStatus("pause");
        service.updateProject(service_project);
        service_project = service.findProject(service_project.getId());
        Assert.assertEquals(service.findProject(service_project.getId()).getStatus(), "pause");
        service.deleteProjectById(service_project.getId());
        // test empty update
        Assert.assertFalse(service.updateProject(service_project));
    }


    @Test
    public void testFindProjectsByStatus() {
        ProjectService service = new ProjectService();
        Project project_1 = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );
        Project project_2 = new Project(
                "project-2", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "pause"
        );
        Project project_3 = new Project(
                "project-3", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );
        service.saveProject(project_1);
        service.saveProject(project_2);
        service.saveProject(project_3);
        List<Project> projects = service.findProjectsByStatus("active");
        int prev_size = projects.size();
        projects.remove(project_1);
        projects.remove(project_3);
        int new_size = projects.size();
        Assert.assertEquals(new_size, prev_size - 2);
        service.deleteProject(project_1);
        service.deleteProject(project_2);
        service.deleteProject(project_3);
    }

    @Test
    public void testFindProjectsByWorker() {
        // create projects
        ProjectService service = new ProjectService();
        Project project_1 = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );
        Project project_2 = new Project(
                "project-2", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "pause"
        );
        service.saveProject(project_1);
        service.saveProject(project_2);
        // create roles
        Role role_1 = new Role(
                worker_1, project_1, "role-3", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), Timestamp.valueOf("2011-10-02 19:48:05")
        );
        Role role_2 = new Role(
                worker_1, project_2, "role-4", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), Timestamp.valueOf("2011-10-02 19:48:05")
        );
        roleService.saveRole(role_1);
        roleService.saveRole(role_2);
        // test
        List<Project> projects = service.findProjectsByWorker(roleService, worker_1);
        int prev_size = projects.size();
        projects.remove(project_1);
        projects.remove(project_2);
        int new_size = projects.size();
        Assert.assertEquals(new_size, prev_size - 2);
        // delete
        roleService.deleteRole(role_1);
        roleService.deleteRole(role_2);
        service.deleteProject(project_1);
        service.deleteProject(project_2);
    }

    @Test
    public void testFindProjectsInPeriod() {
        ProjectService service = new ProjectService();
        Project project_1 = new Project(
                "project-1", "description", Timestamp.valueOf("2011-08-02 18:48:05"),
                Timestamp.valueOf("2011-09-02 18:48:05"), "active"
        );
        Project project_2 = new Project(
                "project-2", "description", Timestamp.valueOf("2011-08-16 18:48:05"),
                Timestamp.valueOf("2011-09-16 18:48:05"), "pause"
        );
        Project project_3 = new Project(
                "project-3", "description", Timestamp.valueOf("2011-09-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );
        service.saveProject(project_1);
        service.saveProject(project_2);
        service.saveProject(project_3);
        List<Project> projects = service.findProjectsInPeriod(Timestamp.valueOf("2011-08-10 18:48:05"),
                Timestamp.valueOf("2011-09-20 18:48:05"));
        int prev_size = projects.size();
        projects.remove(project_2);
        int new_size = projects.size();
        Assert.assertEquals(new_size, prev_size - 1);
        service.deleteProject(project_1);
        service.deleteProject(project_2);
        service.deleteProject(project_3);
    }

    @Test
    public void testFindProjectsByWorkerInPeriod() {
        // create projects
        ProjectService service = new ProjectService();
        Project project_1 = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "active"
        );
        Project project_2 = new Project(
                "project-2", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "pause"
        );
        service.saveProject(project_1);
        service.saveProject(project_2);
        // create roles
        Role role_1 = new Role(
                worker_1, project_1, "role-9", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), Timestamp.valueOf("2011-11-02 19:48:05")
        );
        Role role_2 = new Role(
                worker_2, project_2, "role-0", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), Timestamp.valueOf("2011-10-03 19:48:05")
        );
        roleService.saveRole(role_1);
        roleService.saveRole(role_2);
        // test
        List<Project> projects = service.findProjectsByWorkerInPeriod(roleService, worker_2,
                Timestamp.valueOf("2011-10-01 18:48:05"), Timestamp.valueOf("2011-10-04 18:48:05"));
        int prev_size = projects.size();
        System.out.println(projects);
        System.out.println(project_2);
        projects.remove(project_2);
        int new_size = projects.size();
        Assert.assertEquals(new_size, prev_size - 1);
        // delete
        roleService.deleteRole(role_1);
        roleService.deleteRole(role_2);
        service.deleteProject(project_1);
        service.deleteProject(project_2);
    }

    @Test
    public void testCloseProject() {
        // create projects
        ProjectService service = new ProjectService();
        Project project_1 = new Project(
                "project-1", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                null, "active"
        );
        Project project_2 = new Project(
                "project-2", "description", Timestamp.valueOf("2011-10-02 18:48:05"),
                Timestamp.valueOf("2011-10-02 18:48:05"), "pause"
        );
        service.saveProject(project_1);
        service.saveProject(project_2);
        // create roles
        Role role_1 = new Role(
                worker_1, project_1, "role-9", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), null
        );
        Role role_2 = new Role(
                worker_2, project_2, "role-0", "description",
                Timestamp.valueOf("2011-10-02 18:48:05"), null
        );
        roleService.saveRole(role_1);
        roleService.saveRole(role_2);
        // test true close
        Assert.assertTrue(service.closeProject(roleService, project_1));
        // test project
        java.util.Date date = new java.util.Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Assert.assertEquals(project_1.getEnd_date().toString().substring(0, 10), ts.toString().substring(0, 10));
        Assert.assertEquals(project_1.getStatus(), "Closed");
        // test fake close
        Assert.assertTrue(service.closeProject(roleService, project_2));
        // test project
        Assert.assertEquals(project_2.getEnd_date(), Timestamp.valueOf("2011-10-02 18:48:05"));
        Assert.assertEquals(project_2.getStatus(), "Closed");
        // delete
        roleService.deleteRole(role_1);
        roleService.deleteRole(role_2);
        service.deleteProject(project_1);
        service.deleteProject(project_2);
    }

}
