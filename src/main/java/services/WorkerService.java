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

    public boolean saveWorker(Worker worker) {
        return workerDAO.save(worker);
    }

    public boolean deleteWorker(Worker worker) {
        return workerDAO.delete(worker);
    }

    public boolean deleteWorkerById(int id) {
        return workerDAO.delete(workerDAO.findById(id));
    }

    public boolean updateWorker(Worker worker) {
        return workerDAO.update(worker);
    }

    public List<Worker> findAllWorkers() {
        return workerDAO.findAll();
    }

}
