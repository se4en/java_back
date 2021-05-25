package entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table (name = "payments")
public class Payment {

    @Column(name = "paym_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.EAGER)
    //@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Worker worker;

    private String type;
    private java.sql.Date date;
    private int amount;

    public Payment() {}

    public Payment(Worker worker, String type, Date date_time, int amount) {
        this.worker = worker;
        this.type = type;
        this.date = date;
        this.amount = amount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_time() {
        return date;
    }

    public void setDate_time(Date date_time) {
        this.date = date_time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return id == payment.id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", worker sername=" + worker.getSername() +
                ", type='" + type + '\'' +
                ", date_time=" + date +
                ", amount=" + amount +
                '}';
    }
}
