package services;

import DAO.PaymentDAO;
import entities.Payment;

import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();

    public PaymentService() {
    }

    public Payment findPayment(int id) {
        return paymentDAO.findById(id);
    }

    public boolean savePayment(Payment payment) {
        return paymentDAO.save(payment);
    }

    public boolean deletePayment(Payment payment) {
        return paymentDAO.delete(payment);
    }

    public boolean deletePaymentById(int id) {
        return paymentDAO.delete(paymentDAO.findById(id));
    }

    public boolean updatePayment(Payment payment) {
        return paymentDAO.update(payment);
    }

    public List<Payment> findAllPayments() {
        return paymentDAO.findAll();
    }

}
