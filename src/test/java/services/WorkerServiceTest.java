package services;

import entities.Worker;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.Date;
import java.io.IOException;
import java.sql.SQLException;
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

        service.saveWorker(worker);
        Worker service_worker = service.findWorker(worker.getId());
        Assert.assertEquals(worker, service_worker);
        service.deleteWorkerById(service_worker.getId());
    }

}
