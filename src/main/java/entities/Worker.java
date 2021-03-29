package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table (name = "workers")
public class Worker {

    @Column(name = "work_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sername;
    private String name;
    private String patronymic;
    private java.sql.Date birthdate;
    private String phone;
    private String email;
    private String address;
    private java.sql.Date first_day;
    private java.sql.Date last_day;
    private String post;
    private int salary;

    public Worker() {};

    public Worker(String sername, String name, String patronymic,
                  Date birthdate, String phone, String email, String address,
                  Date first_day, Date last_day, String post, int salary) {
        this.sername = sername;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.first_day = first_day;
        this.last_day = last_day;
        this.post = post;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFirst_day() {
        return first_day;
    }

    public void setFirst_day(Date first_day) {
        this.first_day = first_day;
    }

    public Date getLast_day() {
        return last_day;
    }

    public void setLast_day(Date last_day) {
        this.last_day = last_day;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return id == worker.id &&
                salary == worker.salary &&
                Objects.equals(sername, worker.sername) &&
                Objects.equals(name, worker.name) &&
                Objects.equals(patronymic, worker.patronymic) &&
                Objects.equals(birthdate, worker.birthdate) &&
                Objects.equals(phone, worker.phone) &&
                Objects.equals(email, worker.email) &&
                Objects.equals(address, worker.address) &&
                Objects.equals(first_day, worker.first_day) &&
                Objects.equals(last_day, worker.last_day) &&
                Objects.equals(post, worker.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sername, name, patronymic, birthdate, phone, email, address, first_day, last_day, post, salary);
    }
}
