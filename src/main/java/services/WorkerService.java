package services;

import DAO.WorkerDAO;
import entities.Worker;

import java.util.List;

public class WorkerService {

    private WorkerDAO workerDAO = new WorkerDAO();

    public WorkerService() {
    }

    public Worker findWorker(int id) {
        return workerDAO.findById(id);
    }

    public void saveWorker(Worker worker) {
        workerDAO.save(worker);
    }

    public void deleteWorker(Worker worker) {
        workerDAO.delete(worker);
    }

    public void updateWorker(Worker worker) {
        workerDAO.update(worker);
    }

    public List<Worker> findAllWorkers() {
        return workerDAO.findAll();
    }

}
