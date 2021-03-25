package entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "roles")
public class Role {

    @Column(name = "role_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Worker worker;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "proj_id")
    //private Project project;

    private String role;
    private String description;
    private java.sql.Timestamp start_date;
    private java.sql.Timestamp end_date;


    public long getId() {
        return id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /*public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }*/

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Role other = (Role) obj;
        return (this.id == other.id) &&
                (this.role == other.role) &&
                (this.description == other.description) &&
                (this.worker == other.worker) &&
                (this.start_date == other.start_date) &&
                (this.end_date == other.end_date);
    }
}
