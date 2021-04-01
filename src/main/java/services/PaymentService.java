package services;

import DAO.PaymentDAO;
import entities.Payment;
import entities.Worker;

import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();

    public Payment findPayment(long id) {
        return paymentDAO.findById(id);
    }

    public void savePayment(Payment payment) {
        paymentDAO.save(payment);
    }

    public void deletePayment(Payment payment) {
        paymentDAO.delete(payment);
    }

    public boolean deletePaymentById(long id) {
        Payment payment = paymentDAO.findById(id);
        if (payment == null) {
            return false;
        }
        else {
            paymentDAO.delete(payment);
            return true;
        }
    }

    public void updatePayment(Payment payment) {
        paymentDAO.update(payment);
    }

    public List<Payment> findAllPayments() {
        return paymentDAO.findAll();
    }

    /*
     Return all payments of specific type
     */
    public List<Payment> findPaymentsByType(String type) {
        List<Payment> payments = findAllPayments();
        payments.removeIf(payment -> (!payment.getType().equals(type)));
        return payments;
    }

    /*
     Return all payments for worker
     */
    public List<Payment> findPaymentsByWorker(Worker worker) {
        List<Payment> payments = findAllPayments();
        payments.removeIf(payment -> (payment.getWorker().getId() != worker.getId()));
        return payments;
    }

    /*
     Return all payments for worker in a given time period
     */
    public List<Payment> findPaymentsByWorkerInPeriod(Worker worker, java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Payment> payments = findPaymentsByWorker(worker);
        payments.removeIf(payment -> (payment.getDate_time().before(start_date) | payment.getDate_time().after(end_date)));
        return payments;
    }

    /*
     Return total amount of payments for worker in the time period
     */
    public long getCostsByWorkerInPeriod(Worker worker, java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        long result = 0;
        for (Payment payment : findPaymentsByWorkerInPeriod(worker, start_date, end_date)) {
            result += payment.getAmount();
        }
        return result;
    }

    /*
     Return all payments in a given time period
     */
    public List<Payment> findPaymentsInPeriod(java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        List<Payment> payments = findAllPayments();
        payments.removeIf(payment -> (payment.getDate_time().before(start_date) | payment.getDate_time().after(end_date)));
        return payments;
    }

    /*
     Return total amount of payments in the time period
     */
    public long getCostsInPeriod(java.sql.Timestamp start_date, java.sql.Timestamp end_date) {
        long result = 0;
        for (Payment payment : findPaymentsInPeriod(start_date, end_date)) {
            result += payment.getAmount();
        }
        return result;
    }

}
