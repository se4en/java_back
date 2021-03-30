package services;

import DAO.WorkerDAO;
import entities.Project;
import entities.Worker;

import java.util.List;
import java.util.Date;
import java.util.Objects;

public class WorkerService {

    private WorkerDAO workerDAO = new WorkerDAO();

    public WorkerService() {
    }

    public Worker findWorker(long id) {
        return workerDAO.findById(id);
    }

    public boolean saveWorker(Worker worker) {
        return workerDAO.save(worker);
    }

    public boolean deleteWorker(Worker worker) {
        return workerDAO.delete(worker);
    }

    public boolean deleteWorkerById(long id) {
        return workerDAO.delete(workerDAO.findById(id));
    }

    public boolean updateWorker(Worker worker) {
        return workerDAO.update(worker);
    }

    public List<Worker> findAllWorkers() {
        return workerDAO.findAll();
    }

    /*
     Return all workers on the post
     */
    public List<Worker> findWorkersByPost(String post) {
        List<Worker> workers = findAllWorkers();
        workers.removeIf(worker -> (!Objects.equals(worker.getPost(), post)));
        return workers;
    }

    public boolean dismissWorker(Worker worker) {
        if (worker.getLast_day() != null) {
            return true;
        }
        // get current date
        Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        worker.setLast_day(date);
        return saveWorker(worker);
    }

}
