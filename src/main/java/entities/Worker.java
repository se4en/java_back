package entities;

import javax.persistence.*;
import java.sql.Date;

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
    public String toString() {
        return "entities.Worker{" +
                "id=" + id +
                "', sername='" + sername +
                "', name='" + name +
                "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Worker other = (Worker) obj;
        return (this.id == other.id) &&
                (this.sername == other.sername) &&
                (this.name == other.name) &&
                (this.patronymic == other.patronymic) &&
                (this.birthdate == other.birthdate);
    }
}
