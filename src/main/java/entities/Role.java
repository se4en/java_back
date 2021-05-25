package entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table (name = "roles")
public class Role {

    @Column(name = "role_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.EAGER)
    //@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Worker worker;

    @ManyToOne(fetch=FetchType.EAGER)
    //@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "proj_id")
    private Project project;

    private String role;
    private String description;
    private java.sql.Date start_date;
    private java.sql.Date end_date;

    public Role() {}

    public Role(Worker worker, Project project, String role,
                String description, Date start_date, Date end_date) {
        this.worker = worker;
        this.project = project;
        this.role = role;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public long getId() {
        return id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role1 = (Role) o;
        return id == role1.id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", worker=" + worker +
                ", project=" + project +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
