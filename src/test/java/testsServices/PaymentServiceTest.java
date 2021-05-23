package testsServices;

import entities.Payment;
import entities.Worker;
import services.WorkerService;
import services.PaymentService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PaymentServiceTest {
    public WorkerService workerService;
    public Worker worker_1, worker_2, worker_3;

    @BeforeClass
    public void prepForTests() {
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
    public void testSaveFindPayment() {
        PaymentService service = new PaymentService();
        Payment payment = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);

        // test save-find
        service.savePayment(payment);
        Payment service_payment = service.findPayment(payment.getId());
        Assert.assertEquals(service_payment, payment);
        service.deletePaymentById(service_payment.getId());
        // test empty find
        Assert.assertNull(service.findPayment(payment.getId()));
    }

    @Test
    public void testDeletePayment() {
        PaymentService service = new PaymentService();
        Payment payment = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);

        // test delete by id
        service.savePayment(payment);
        service.deletePaymentById(payment.getId());
        Assert.assertNull(service.findPayment(payment.getId()));
        // test delete
        service.savePayment(payment);
        service.deletePayment(payment);
        Assert.assertNull(service.findPayment(payment.getId()));
    }

    @Test
    public void testUpdatePayment() {
        PaymentService service = new PaymentService();
        Payment payment = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);

        // test save-update
        service.savePayment(payment);
        Payment service_payment = service.findPayment(payment.getId());
        service_payment.setType("type-2");
        service.updatePayment(service_payment);
        service_payment = service.findPayment(service_payment.getId());
        Assert.assertEquals(service.findPayment(service_payment.getId()).getType(), "type-2");
        service.deletePaymentById(service_payment.getId());
    }

    @Test
    public void testFindAllPayments() {
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_1, "type-2", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_3= new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        List<Payment> payments = service.findAllPayments();
        int prev_size = payments.size();
        payments.remove(payment_1);
        payments.remove(payment_2);
        payments.remove(payment_3);
        int new_size = payments.size();
        Assert.assertEquals(new_size, prev_size - 3);
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testFindPaymentsByType() {
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_1, "type-2", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_3= new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        List<Payment> payments = service.findPaymentsByType("type-1");
        int prev_size = payments.size();
        payments.remove(payment_1);
        payments.remove(payment_3);
        int new_size = payments.size();
        Assert.assertEquals(new_size, prev_size - 2);
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testFindPaymentsByWorker() {
        // create payments
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_3, "type-2", Date.valueOf("2011-10-02 18:48:05"),10000);
        Payment payment_3 = new Payment(worker_3, "type-1", Date.valueOf("2011-10-02 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        // test
        List<Payment> payments = service.findPaymentsByWorker(worker_3);
        int prev_size = payments.size();
        payments.remove(payment_2);
        payments.remove(payment_3);
        int new_size = payments.size();
        Assert.assertEquals(new_size, prev_size - 2);
        // delete
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testFindPaymentsInPeriod() {
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-06 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_3, "type-2", Date.valueOf("2011-10-12 18:48:05"),10000);
        Payment payment_3 = new Payment(worker_3, "type-1", Date.valueOf("2011-10-18 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        List<Payment> payments = service.findPaymentsInPeriod(Date.valueOf("2011-10-10 18:48:05"),
                Date.valueOf("2011-10-20 18:48:05"));
        int prev_size = payments.size();
        payments.remove(payment_2);
        payments.remove(payment_3);
        int new_size = payments.size();
        Assert.assertEquals(new_size, prev_size - 2);
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testFindPaymentsByWorkerInPeriod() {
        // create payments
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_2, "type-1", Date.valueOf("2011-10-06 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_3, "type-2", Date.valueOf("2011-10-12 18:48:05"),10000);
        Payment payment_3 = new Payment(worker_3, "type-1", Date.valueOf("2011-10-18 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        // test
        List<Payment> payments = service.findPaymentsByWorkerInPeriod(worker_3, Date.valueOf("2011-10-01 18:48:05"),
                Date.valueOf("2011-10-30 18:48:05"));
        int prev_size = payments.size();
        payments.remove(payment_2);
        payments.remove(payment_3);
        int new_size = payments.size();
        Assert.assertEquals(new_size, prev_size - 2);
        // delete
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testGetCostsByWorkerInPeriod() {
        // create payments
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-06 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_2, "type-2", Date.valueOf("2011-10-12 18:48:05"),10000);
        Payment payment_3 = new Payment(worker_2, "type-1", Date.valueOf("2011-10-18 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        // test
        Assert.assertEquals(service.getCostsByWorkerInPeriod(worker_2, Date.valueOf("2011-10-10 18:48:05"),
                Date.valueOf("2011-10-15 18:48:05")), 10000);
        // delete
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

    @Test
    public void testGetCostsInPeriod() {
        // create payments
        PaymentService service = new PaymentService();
        Payment payment_1 = new Payment(worker_1, "type-1", Date.valueOf("2011-10-06 18:48:05"),10000);
        Payment payment_2 = new Payment(worker_2, "type-2", Date.valueOf("2011-10-12 18:48:05"), 5000);
        Payment payment_3 = new Payment(worker_3, "type-1", Date.valueOf("2011-10-18 18:48:05"),10000);
        service.savePayment(payment_1);
        service.savePayment(payment_2);
        service.savePayment(payment_3);
        // test
        Assert.assertEquals(
                service.getCostsInPeriod(Date.valueOf("2011-10-01 18:48:05"), Date.valueOf("2011-10-20 18:48:05")) -
                        service.getCostsInPeriod(Date.valueOf("2011-10-10 18:48:05"), Date.valueOf("2011-10-20 18:48:05")),
                10000);
        // delete
        service.deletePayment(payment_1);
        service.deletePayment(payment_2);
        service.deletePayment(payment_3);
    }

}
