package controllers;

import entities.Payment;
import entities.Project;
import entities.Role;
import entities.Worker;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.PaymentService;
import services.ProjectService;
import services.WorkerService;
import services.RoleService;

import java.sql.Date;


import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Controller
@RequestMapping("/worker")
public class WorkerController {
    WorkerService workerService = new WorkerService();
    RoleService roleService = new RoleService();
    PaymentService paymentService = new PaymentService();
    ProjectService projectService = new ProjectService();

    @GetMapping("/all")
    public String workerListPage(Model model) {
        List<Worker> workers = workerService.findAllWorkers();
        model.addAttribute("workers", workers);
        return "worker/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteWorker(@PathVariable(value = "id") long id) {

        workerService.deleteWorkerById(id);
        return "redirect:/worker/all";
    }

    @GetMapping("/dismiss/{id}")
    public String closeWorker(@PathVariable(value = "id") long id) {
        Worker worker = workerService.findWorker(id);
        workerService.dismissWorker(worker);
        return "redirect:/worker/all";
    }

    @GetMapping("/info/{id}")
    public String workerInfoPage(@PathVariable(value = "id") long id, Model model) {
        Worker worker = workerService.findWorker(id);
        model.addAttribute("worker", worker);

        List<Payment> payments = paymentService.findPaymentsByWorker(worker);
        model.addAttribute("payments", payments);

        List<Project> projects = projectService.findProjectsByWorker(roleService, worker);
        model.addAttribute("projects", projects);

        List<Role> roles = roleService.findRolesByWorker(worker);
        model.addAttribute("roles", roles);
        return "worker/info";
    }

    @GetMapping("/role/{id}")
    public String createRolePage(@PathVariable(value = "id") long id, Model model) {
        Worker worker = workerService.findWorker(id);
        model.addAttribute("worker", worker);

        Role role = new Role();
        model.addAttribute("role", role);

        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "worker/role";
    }

    @PostMapping("/saverole")
    public String saveRoleWorker(@RequestParam(name = "r_id", required = false) Long r_id,
                             @RequestParam(name = "r_w_id") Long r_w_id,
                             @RequestParam(name = "r_project", required = false) int r_project,
                             @RequestParam(name = "r_role") String r_role,
                             @RequestParam(name = "r_description") String r_description,
                             @RequestParam(name = "r_start_date") String r_start_date,
                             @RequestParam(name = "r_end_date") String r_end_date,
                             Model model) {
        Role role = new Role();
        // check end date
        if (r_start_date != "") {
            try {
                Date start_date = Date.valueOf(r_start_date);
                role.setStart_date(start_date);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + r_start_date);
                return "error";
            }
        }
        // check end date
        if (r_project > 0) {
            try {
                Date end_date = Date.valueOf(r_end_date);
                role.setEnd_date(end_date);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + r_end_date);
                return "error";
            }
        }
        // check worker
        if (r_project > 0) {
            try {
                Project project = projectService.findProject(r_project);
                if (project != null) {
                    role.setProject(project);
                } else {
                    model.addAttribute("link", "/worker/all");
                    model.addAttribute("error_msg", "Incorrect project: " + r_project);
                    return "error";
                }
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect project: " + r_project);
                return "error";
            }
        }
        // check worker
        if (r_w_id > 0) {
            try {
                Worker ok_worker = workerService.findWorker(r_w_id);
                if (ok_worker != null) {
                    role.setWorker(ok_worker);
                } else {
                    model.addAttribute("link", "/worker/all");
                    model.addAttribute("error_msg", "Incorrect worker: " + r_w_id);
                    return "error";
                }
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect project: " + r_w_id);
                return "error";
            }
        }
        // check name
        if (r_description != "") {
            role.setDescription(r_description);
        }
        // check sername
        if (r_role != "") {
            role.setRole(r_role);
        }
        roleService.saveRole(role);
        return "redirect:/worker/all";
    }

    @GetMapping("/new")
    public String newWorker(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        return "worker/new";
    }

    @PostMapping("/save")
    public String saveWorker(@RequestParam(name = "w_id", required = false) Long w_id,
                             @RequestParam(name = "w_name") String w_name,
                             @RequestParam(name = "w_sername") String w_sername,
                             @RequestParam(name = "w_patronymic") String w_patronymic,
                             @RequestParam(name = "w_birthdate") String w_birthdate,
                             @RequestParam(name = "w_phone") String w_phone,
                             @RequestParam(name = "w_email") String w_email,
                             @RequestParam(name = "w_address") String w_address,
                             @RequestParam(name = "w_first_day") String w_first_day,
                             @RequestParam(name = "w_last_day") String w_last_day,
                             @RequestParam(name = "w_post") String w_post,
                             @RequestParam(name = "w_salary") String w_salary,
                             Model model) {
        Worker worker = new Worker();
        // check birthdate
        if (w_birthdate != "") {
            try {
                Date birthdate = Date.valueOf(w_birthdate);
                worker.setBirthdate(birthdate);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_birthdate);
                return "error";
            }
        }
        // check first_day
        if (w_first_day != "") {
            try {
                Date first_day = Date.valueOf(w_first_day);
                worker.setFirst_day(first_day);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_first_day);
                return "error";
            }
        }
        // check last_day
        if (w_last_day != "") {
            try {
                Date last_day = Date.valueOf(w_last_day);
                worker.setLast_day(last_day);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_last_day);
                return "error";
            }
        }
        // check name
        if (w_name != "") {
            worker.setName(w_name);
        }
        // check sername
        if (w_sername != "") {
            worker.setSername(w_sername);
        }
        // check patronymic
        if (w_patronymic != "") {
            worker.setPatronymic(w_patronymic);
        }
        // check phone
        if (w_phone != "") {
            worker.setPhone(w_phone);
        }
        // check email
        if (w_email != "") {
            worker.setEmail(w_email);
        }
        // check address
        if (w_address != "") {
            worker.setAddress(w_address);
        }
        // check post
        if (w_post != "") {
            worker.setPost(w_post);
        }
        // check salary
        if (w_salary != "") {
            try {
                int salary = Integer.parseInt(w_salary);
                worker.setSalary(salary);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect salary format: " + w_salary);
                return "error";
            }
        }
        workerService.saveWorker(worker);
        return "redirect:/worker/all";
    }

    @GetMapping("/update/{id}")
    public String updateWorker(@PathVariable(value = "id") long id, Model model) {
        Worker worker = workerService.findWorker(id);

        model.addAttribute("worker", worker);
        return "worker/update";
    }

    @PostMapping("/update")
    public String saveAfterUpdateWorker(@RequestParam(name = "w_id", required = false) Long w_id,
                                        @RequestParam(name = "w_name") String w_name,
                                        @RequestParam(name = "w_sername") String w_sername,
                                        @RequestParam(name = "w_patronymic") String w_patronymic,
                                        @RequestParam(name = "w_birthdate") String w_birthdate,
                                        @RequestParam(name = "w_phone") String w_phone,
                                        @RequestParam(name = "w_email") String w_email,
                                        @RequestParam(name = "w_address") String w_address,
                                        @RequestParam(name = "w_first_day") String w_first_day,
                                        @RequestParam(name = "w_last_day") String w_last_day,
                                        @RequestParam(name = "w_post") String w_post,
                                        @RequestParam(name = "w_salary") String w_salary,
                                        Model model) {
        Worker worker = workerService.findWorker(w_id);
        // check birthdate
        if (w_birthdate != "") {
            try {
                Date birthdate = Date.valueOf(w_birthdate);
                worker.setBirthdate(birthdate);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_birthdate);
                return "error";
            }
        }
        // check first_day
        if (w_first_day != "") {
            try {
                Date first_day = Date.valueOf(w_first_day);
                worker.setFirst_day(first_day);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_first_day);
                return "error";
            }
        }
        // check last_day
        if (w_last_day != "") {
            try {
                Date last_day = Date.valueOf(w_last_day);
                worker.setLast_day(last_day);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect date format: " + w_last_day);
                return "error";
            }
        }
        // check name
        if (w_name != "") {
            worker.setName(w_name);
        }
        // check sername
        if (w_sername != "") {
            worker.setSername(w_sername);
        }
        // check patronymic
        if (w_patronymic != "") {
            worker.setPatronymic(w_patronymic);
        }
        // check phone
        if (w_phone != "") {
            worker.setPhone(w_phone);
        }
        // check email
        if (w_email != "") {
            worker.setEmail(w_email);
        }
        // check address
        if (w_address != "") {
            worker.setAddress(w_address);
        }
        // check post
        if (w_post != "") {
            worker.setPost(w_post);
        }
        // check salary
        if (w_salary != "") {
            try {
                int salary = Integer.parseInt(w_salary);
                worker.setSalary(salary);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect salary format: " + w_salary);
                return "error";
            }
        }
        workerService.updateWorker(worker);
        return "redirect:/worker/all";
    }
}
