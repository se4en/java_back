package testsServices;

import entities.Worker;

import org.testng.Assert;
import org.testng.annotations.Test;
import services.WorkerService;

import java.sql.Date;
import java.util.List;

public class WorkerServiceTest {

    @Test
    public void testSaveFindWorker() {
        WorkerService service = new WorkerService();
        Worker worker = new Worker(
                "Ar-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );

        // test save-find
        service.saveWorker(worker);
        Worker service_worker = service.findWorker(worker.getId());
        Assert.assertEquals(service_worker, worker);
        service.deleteWorkerById(service_worker.getId());
        // test empty find
        Assert.assertEquals(service.findWorker(worker.getId()), null);
    }

    @Test
    public void testDeleteWorker() {
        WorkerService service = new WorkerService();
        Worker worker = new Worker(
                "Bayan-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );

        // test delete by id
        service.saveWorker(worker);
        service.deleteWorkerById(worker.getId());
        Assert.assertEquals(service.findWorker(worker.getId()), null);
        // test delete
        service.saveWorker(worker);
        service.deleteWorker(worker);
        Assert.assertEquals(service.findWorker(worker.getId()), null);
        // test empty delete
        service.deleteWorker(worker);
        Assert.assertEquals(service.findWorker(worker.getId()), null);
    }

    @Test
    public void testUpdateWorker() {
        WorkerService service = new WorkerService();
        Worker worker = new Worker(
                "Br-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );

        // test save-update
        service.saveWorker(worker);
        Worker service_worker = service.findWorker(worker.getId());
        service_worker.setSalary(200000);
        service.updateWorker(service_worker);
        service_worker = service.findWorker(service_worker.getId());
        Assert.assertEquals(service.findWorker(service_worker.getId()).getSalary(), 200000);
        service.deleteWorkerById(service_worker.getId());
        // test empty update
        Assert.assertEquals(service.updateWorker(service_worker), false);
    }


    @Test
    public void testFindAllWorkers() {
        WorkerService service = new WorkerService();
        Worker worker_1 = new Worker(
                "Fr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );
        Worker worker_2 = new Worker(
                "Gr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );
        Worker worker_3 = new Worker(
                "Hr-ool", "Anton", "Olegovich", Date.valueOf("1989-03-23"),
                "+79995657112", "bran@yandex.ru", "MOSCOW, Shosse Entuziastov, 222/1, 246",
                Date.valueOf("2020-10-13"), Date.valueOf("2020-10-17"), "director", 150000
        );

        service.saveWorker(worker_1);
        service.saveWorker(worker_2);
        service.saveWorker(worker_3);
        List<Worker> workers = service.findAllWorkers();
        int prev_size = workers.size();
        workers.remove(worker_1);
        workers.remove(worker_2);
        workers.remove(worker_3);
        int new_size = workers.size();
        Assert.assertEquals(new_size, prev_size-3);
        service.deleteWorker(worker_1);
        service.deleteWorker(worker_2);
        service.deleteWorker(worker_3);
    }

}
