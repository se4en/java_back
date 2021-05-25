package controllers;

import entities.Payment;
import entities.Project;
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
import services.RoleService;
import services.WorkerService;

import java.sql.Date;


import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Controller
@RequestMapping("/payment")
public class PaymentController {
    PaymentService paymentService = new PaymentService();
    WorkerService workerService = new WorkerService();

    @GetMapping("/all")
    public String paymentListPage(Model model) {
        List<Payment> payments = paymentService.findAllPayments();
        model.addAttribute("payments", payments);
        return "payment/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {
        paymentService.deletePaymentById(id);
        return "redirect:/payment/all";
    }

    @GetMapping("/new")
    public String newPayment(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payment/new";
    }

    @PostMapping("/save")
    public String savePayment(@RequestParam(name = "p_id", required = false) Long p_id,
                              @RequestParam(name = "p_worker", required = false) Worker p_worker,
                              @RequestParam(name = "p_type") String p_type,
                              @RequestParam(name = "p_date") String p_date,
                              @RequestParam(name = "p_amount") String p_amount,
                              Model model) {
        Payment payment = new Payment();
        // check date
        if (p_date != "") {
            try {
                Date date = Date.valueOf(p_date);
                payment.setDate_time(date);
            } catch (Exception e) {
                model.addAttribute("link", "/payment/all");
                model.addAttribute("error_msg", "Incorrect date format: " + p_date);
                return "error";
            }
        }
        // check worker
        if (p_worker != null) {
            try {
                Worker ok_worker = workerService.findWorker(p_worker.getId());
                if (ok_worker != null) {
                    payment.setWorker(ok_worker);
                } else {
                    model.addAttribute("link", "/payment/all");
                    model.addAttribute("error_msg", "Incorrect worker: " + p_worker.getId());
                    return "error";
                }
            } catch (Exception e) {
                model.addAttribute("link", "/payment/all");
                model.addAttribute("error_msg", "Incorrect worker: " + p_worker.getId());
                return "error";
            }
        }
        // check amount
        if (p_amount != "") {
            try {
                int amount = Integer.parseInt(p_amount);
                payment.setAmount(amount);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect amount format: " + p_amount);
                return "error";
            }
        }
        // check type
        if (p_type != "") {
            payment.setType(p_type);
        }
        paymentService.savePayment(payment);
        return "redirect:/payment/all";
    }

    @GetMapping("/update/{id}")
    public String updatePayment(@PathVariable(value = "id") long id, Model model) {
        Payment payment = paymentService.findPayment(id);

        model.addAttribute("payment", payment);
        return "payment/update";
    }

    @PostMapping("/update")
    public String saveAfterUpdatePayment(@RequestParam(name = "p_id", required = false) Long p_id,
                                         @RequestParam(name = "p_worker", required = false) Worker p_worker,
                                         @RequestParam(name = "p_type") String p_type,
                                         @RequestParam(name = "p_date") String p_date,
                                         @RequestParam(name = "p_amount") String p_amount,
                                         Model model) {
        Payment payment = paymentService.findPayment(p_id);
        // check date
        if (p_date != "") {
            try {
                Date date = Date.valueOf(p_date);
                payment.setDate_time(date);
            } catch (Exception e) {
                model.addAttribute("link", "/payment/all");
                model.addAttribute("error_msg", "Incorrect date format: " + p_date);
                return "error";
            }
        }
        // check worker
        if (p_worker != null) {
            try {
                Worker ok_worker = workerService.findWorker(p_worker.getId());
                if (ok_worker != null) {
                    payment.setWorker(ok_worker);
                } else {
                    model.addAttribute("link", "/payment/all");
                    model.addAttribute("error_msg", "Incorrect worker: " + p_worker.getId());
                    return "error";
                }
            } catch (Exception e) {
                model.addAttribute("link", "/payment/all");
                model.addAttribute("error_msg", "Incorrect worker: " + p_worker.getId());
                return "error";
            }
        }
        // check amount
        if (p_amount != "") {
            try {
                int amount = Integer.parseInt(p_amount);
                payment.setAmount(amount);
            } catch (Exception e) {
                model.addAttribute("link", "/worker/all");
                model.addAttribute("error_msg", "Incorrect amount format: " + p_amount);
                return "error";
            }
        }
        // check type
        if (p_type != "") {
            payment.setType(p_type);
        }
        paymentService.updatePayment(payment);
        return "redirect:/payment/all";
    }
}
