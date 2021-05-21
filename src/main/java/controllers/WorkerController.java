package controllers;

import DAO.WorkerDAO;
import entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repositories.WorkerRepository;
import services.WorkerService;

import java.util.List;

@Controller
@EntityScan( basePackages = {"entities"} )
public class WorkerController {

    //@Autowired(required=false)
    //private WorkerRepository repository;
    @Autowired
    //private WorkerDAO workerDAO;
    private WorkerService workerService;

    @GetMapping("/workers")
    public String allWorkersPage(Model model) {
        List<Worker> workers = workerService.findAllWorkers();
        //List<Worker> workers = repository.findAllWorkers();
        model.addAttribute("workers", workers);
        return "workers";
    }
}
