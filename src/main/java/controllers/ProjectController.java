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

import java.sql.Date;


import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Controller
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService = new ProjectService();

    @GetMapping("/all")
    public String projectListPage(Model model) {
        List<Project> projects = projectService.findAllProjects();  // todo load not all films
        model.addAttribute("projects", projects);
        return "project/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable(value = "id") long id) {

        projectService.deleteProjectById(id);
        return "redirect:/project/all";
    }

}
