package controllers;

import entities.Project;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.ProjectService;
import services.RoleService;

import java.sql.Date;


import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Controller
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService = new ProjectService();
    RoleService roleService = new RoleService();

    @GetMapping("/all")
    public String projectListPage(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable(value = "id") long id) {

        projectService.deleteProjectById(id);
        return "redirect:/project/all";
    }

    @GetMapping("/close/{id}")
    public String closeProject(@PathVariable(value = "id") long id) {
        Project project = projectService.findProject(id);
        projectService.closeProject(roleService, project);
        return "redirect:/project/all";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "project/new";
    }

    @PostMapping("/save")
    public String saveProject(@RequestParam(name = "p_id", required = false) Long project_id,
                               @RequestParam(name = "p_title") String p_title,
                               @RequestParam(name = "p_start_date") Date p_start_date,
                               @RequestParam(name = "p_end_date") Date p_end_date,
                               @RequestParam(name = "p_status") String p_status,
                               @RequestParam(name = "p_description") String p_description,
                               Model model) {
        Project project = new Project();
        project.setTitle(p_title);
        project.setStart_date(p_start_date);
        project.setEnd_date(p_end_date);
        project.setStatus(p_status);
        project.setDescription(p_description);
        projectService.saveProject(project);
        return "redirect:/project/all";
    }

    @GetMapping("/update/{id}")
    public String updateProject(@PathVariable(value = "id") long id, Model model) {
        Project project = projectService.findProject(id);

        model.addAttribute("project", project);
        return "project/update";
    }

    @PostMapping("/update")
    public String saveAfterUpdateProject(@RequestParam(name = "p_id", required = false) Long project_id,
                                         @RequestParam(name = "p_title") String p_title,
                                         @RequestParam(name = "p_start_date") String p_start_date,
                                         @RequestParam(name = "p_end_date") String p_end_date,
                                         @RequestParam(name = "p_status") String p_status,
                                         @RequestParam(name = "p_description") String p_description,
                                         Model model) {
        Project project = projectService.findProject(project_id);
        // check id
        if (project == null) {
            model.addAttribute("error_msg", "There is no project with id=" + project);
            return "error";
        }
        System.out.println("OK");
        System.out.println(p_start_date);
        // check start date
        if (p_start_date != null) {
            try {
                Date start_date = Date.valueOf(p_start_date);
                project.setStart_date(start_date);
            } catch (Exception e) {
                model.addAttribute("link", "/project/all");
                model.addAttribute("error_msg", "Incorrect date format: " + p_start_date);
                return "error";
            }
        }
        System.out.println("OK_2");

        if (project != null) {
            project.setTitle(p_title);
            //project.setStart_date(p_start_date);
            //project.setEnd_date(p_end_date);
            project.setStatus(p_status);
            project.setDescription(p_description);

            projectService.updateProject(project);
        }
        System.out.println("OK_3");
        return "redirect:/project/all";
    }
}
