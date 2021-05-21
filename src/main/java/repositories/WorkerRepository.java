package repositories;

import java.util.List;

import DAO.WorkerDAO;
import entities.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import services.WorkerService;

@Repository
public interface WorkerRepository extends CrudRepository<WorkerService, Long> {

//    List<Worker> findAllWorkers();
    List<Worker> findAllWorkers();
    //Worker findById(long id);
}